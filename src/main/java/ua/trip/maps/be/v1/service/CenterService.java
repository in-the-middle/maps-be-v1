package ua.trip.maps.be.v1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.trip.maps.be.v1.geometry.model.Point;
import ua.trip.maps.be.v1.geometry.service.ConvexHullService;
import ua.trip.maps.be.v1.geometry.service.PolygonPointGenerator;
import ua.trip.maps.be.v1.utils.MappingUtils;
import ua.trip.maps.be.v1.valhalla.consumer.ValhallaApiConsumer;
import ua.trip.maps.be.v1.valhalla.model.*;
import ua.trip.maps.service.model.CenterInputDTO;
import ua.trip.maps.service.model.CenterOutputDTO;
import ua.trip.maps.service.model.LocationDTO;
import ua.trip.maps.service.model.UserInfoDTO;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static java.lang.Math.abs;


@Service
public class CenterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CenterService.class);

    private static final Integer NUMBER_OF_POINTS_TO_GENERATE = 50;

    private static final Integer NUMBER_OF_ITERATIONS = 5;

    @Autowired
    MatrixService matrixService;

    @Autowired
    PolygonPointGenerator polygonPointGenerator;

    @Autowired
    ConvexHullService convexHullService;

    @Autowired
    ValhallaApiConsumer valhallaApiConsumer;

    //todo fix for two points
    public CenterOutputDTO getCenter(CenterInputDTO centerInputDTO) {
        List<UserInfoDTO> users = centerInputDTO.getUsers();
        List<LocationValhallaModel> sources = users.stream()
                .map(UserInfoDTO::getLocation)
                .map(locationDTO -> {
                    LocationValhallaModel location = new LocationValhallaModel();
                    location.setLat(locationDTO.getLat());
                    location.setLon(locationDTO.getLon());
                    return location;
                }).collect(Collectors.toList());
        List<String> costings = users.stream()
                .map(UserInfoDTO::getMode)
                .map(MappingUtils::map)
                .collect(Collectors.toList());

        List<Point> currentState = centerInputDTO.getUsers().stream()
                .map(UserInfoDTO::getLocation)
                .map(locationDTO -> new Point(locationDTO.getLat(), locationDTO.getLon()))
                .collect(Collectors.toList());
        List<Point> convexHull;

        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            if(currentState.size() == 2){
                double y1 = currentState.get(0).getY(), y2 = currentState.get(1).getY();
                double x1 = currentState.get(0).getX(), x2 = currentState.get(1).getX();
                double diff = abs(x2 - x1);
                double midX = (x1 + x2) / 2.0;
                double midY = (y1 + y2) / 2.0;
                if(x1 == x2) {
                    x1 += 0.0005;
                }
                double k = (y1 - y2) / (x1 - x2);
                double kPer = -1.0 / k;
                double bPer = midY + midX/ k;
                double x1Per = midX + diff / 5, x2Per = midX - diff / 5;
                double y1Per = bPer + kPer * x1Per, y2Per = bPer + kPer * x2Per;
                currentState.add(new Point(x1Per, y1Per));
                currentState.add(new Point(x2Per, y2Per));
            }
            convexHull = convexHullService.buildConvexHull(currentState);
            List<Point> equallyDistributedPoints = polygonPointGenerator.generatePoints(convexHull, NUMBER_OF_POINTS_TO_GENERATE);
            List<LocationValhallaModel> targets = equallyDistributedPoints.stream()
                    .map(point -> {
                        LocationValhallaModel target = new LocationValhallaModel();
                        target.setLat(point.getX());
                        target.setLon(point.getY());
                        return target;
                    }).collect(Collectors.toList());
            List<Integer> maximumTimeToPoint = new ArrayList<>(Collections.nCopies(targets.size(), 0));
            List<Future<MatrixOutputValhallaModel>> futures = new ArrayList<>();
            for (int j = 0; j < sources.size(); j++) {
                MatrixInputValhallaModel matrixInputValhallaModel = buildMatrixInput(sources.get(j), targets, costings.get(j));
                futures.add(matrixService.getMatrixAsync(matrixInputValhallaModel));
//                List<Integer> times = getTimes(sources.get(j), targets, costings.get(j));
//                for(int k = 0; k < targets.size(); k++){
//                    Integer maxTime = Math.max(maximumTimeToPoint.get(k), times.get(k));
//                    maximumTimeToPoint.set(k, maxTime);
//                }
            }
            for (Future<MatrixOutputValhallaModel> future : futures) {
                List<Integer> times = null;
                try {
                    times = getTimes(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    LOGGER.error(e.getMessage(), e);
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }
                for (int k = 0; k < targets.size(); k++) {
                    Integer maxTime = Math.max(maximumTimeToPoint.get(k), times.get(k));
                    maximumTimeToPoint.set(k, maxTime);
                }
            }
            List<Map.Entry<Integer, Integer>> pairMaximumTimeToPointPointIndex = new ArrayList<>();
            for (int j = 0; j < targets.size(); j++) {
                pairMaximumTimeToPointPointIndex.add(Map.entry(maximumTimeToPoint.get(j), j));
            }
            List<Integer> sortedByMaximumTimePointIndexes = pairMaximumTimeToPointPointIndex.stream()
                    .sorted((a, b) -> a.getKey() < b.getKey() ? -1 : 1)
                    .limit(15)
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            currentState.clear();
            for (Integer sortedByMaximumTimePointIndex : sortedByMaximumTimePointIndexes) {
                currentState.add(equallyDistributedPoints.get(sortedByMaximumTimePointIndex));
            }
        }

        CenterOutputDTO centerOutputDTO = new CenterOutputDTO();
        LocationDTO result = new LocationDTO();
        result.setLat(currentState.get(0).getX());
        result.setLon(currentState.get(0).getY());
        centerOutputDTO.setLocation(result);
        return centerOutputDTO;
    }

    private List<Integer> getTimes(MatrixOutputValhallaModel matrixOutputValhallaModel) {
        List<SourceToTargetValhallaModel> sourceToTargets = matrixOutputValhallaModel.getSourcesToTargets().get(0);
        return sourceToTargets.stream()
                .map(SourceToTargetValhallaModel::getTime).collect(Collectors.toList());
    }

    private MatrixInputValhallaModel buildMatrixInput(LocationValhallaModel source, List<LocationValhallaModel> targets, String costing) {
        MatrixInputValhallaModel matrixInputValhallaModel = new MatrixInputValhallaModel();

        matrixInputValhallaModel.setSources(Collections.singletonList(source));
        matrixInputValhallaModel.setTargets(targets);
        matrixInputValhallaModel.setCosting(costing);

        return matrixInputValhallaModel;
    }
}