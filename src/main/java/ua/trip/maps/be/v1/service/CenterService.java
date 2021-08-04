package ua.trip.maps.be.v1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.trip.maps.be.v1.geometry.model.Point;
import ua.trip.maps.be.v1.geometry.service.ConvexHullService;
import ua.trip.maps.be.v1.geometry.service.PolygonPointGenerator;
import ua.trip.maps.be.v1.utils.MappingUtils;
import ua.trip.maps.be.v1.valhalla.model.*;
import ua.trip.maps.service.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

@Service
public class CenterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CenterService.class);

    @Value("${NUMBER_OF_POINTS_TO_GENERATE}")
    private Integer numberOfPointsToGenerate;

    @Value("${NUMBER_OF_ITERATIONS}")
    private Integer numberOfIterations;

    @Value("${NUMBER_OF_BEST_POINTS_FILTER}")
    private Integer numberOfBestPointsFilter;

    @Autowired
    MatrixService matrixService;

    @Autowired
    RouteService routeService;

    @Autowired
    PolygonPointGenerator polygonPointGenerator;

    @Autowired
    ConvexHullService convexHullService;

    //todo fix for two points
    public CenterOutputDTO getCenter(CenterInputDTO centerInputDTO) {
        LOGGER.info("Start calculation of center point");
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

        List<CostingOptionsValhallaModel> costingOptionsValhallaModels = users.stream()
                .map(userInfoDTO -> {
                    CostingOptionsValhallaModel costingOptionsValhallaModel = new CostingOptionsValhallaModel();
                    AutoCostingOptionValhallaModel autoCostingOptionValhallaModel = new AutoCostingOptionValhallaModel();
                    if (Boolean.FALSE.equals(userInfoDTO.getIncludeFerries())) {
                        autoCostingOptionValhallaModel.setUseFerry(0);
                    }
                    if (Boolean.FALSE.equals(userInfoDTO.getIncludeHighways())) {
                        autoCostingOptionValhallaModel.setUseHighways(0);
                    }
                    if (Boolean.FALSE.equals(userInfoDTO.getIncludeTolls())) {
                        autoCostingOptionValhallaModel.setUseTolls(0);
                        autoCostingOptionValhallaModel.setTollBoothPenalty(10000);
                    }
                    costingOptionsValhallaModel.setAutoCostingOptionValhallaModel(autoCostingOptionValhallaModel);
                    return costingOptionsValhallaModel;
                }).collect(Collectors.toList());

        List<Point> currentState = getInitialState(centerInputDTO);
        List<Point> convexHull;

        for (int i = 0; i < numberOfIterations; i++) {
            LOGGER.info("Calculating center: currentIteration=[{}], currentStateSize=[{}]", i + 1, currentState.size());
            preProcessCurrentState(currentState);
            convexHull = convexHullService.buildConvexHull(currentState);
            List<Point> equallyDistributedPoints = polygonPointGenerator.generatePoints(convexHull, numberOfPointsToGenerate);
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
                MatrixInputValhallaModel matrixInputValhallaModel = buildMatrixInput(sources.get(j), targets, costings.get(j), costingOptionsValhallaModels.get(j));
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
                    Integer maxTime = Math.max(maximumTimeToPoint.get(k), times.get(k) == null ? 100000 : times.get(k));
                    maximumTimeToPoint.set(k, maxTime);
                }
            }
            List<Map.Entry<Integer, Integer>> pairMaximumTimeToPointPointIndex = new ArrayList<>();
            for (int j = 0; j < targets.size(); j++) {
                pairMaximumTimeToPointPointIndex.add(Map.entry(maximumTimeToPoint.get(j), j));
            }
            List<Integer> sortedByMaximumTimePointIndexes = pairMaximumTimeToPointPointIndex.stream()
                    .sorted(Map.Entry.comparingByKey())
                    .limit(numberOfBestPointsFilter)
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            currentState.clear();
            for (Integer sortedByMaximumTimePointIndex : sortedByMaximumTimePointIndexes) {
                currentState.add(equallyDistributedPoints.get(sortedByMaximumTimePointIndex));
            }
        }
        LOGGER.info("End calculation of center point");
        CenterOutputDTO centerOutputDTO = new CenterOutputDTO();
        LocationDTO result = new LocationDTO();
        result.setLat(currentState.get(0).getX());
        result.setLon(currentState.get(0).getY());
        centerOutputDTO.setLocation(result);
        return centerOutputDTO;
    }

    private void preProcessCurrentState(List<Point> currentState) {
        if (currentState.size() == 2) {
            double y1 = currentState.get(0).getY(), y2 = currentState.get(1).getY();
            double x1 = currentState.get(0).getX(), x2 = currentState.get(1).getX();
            double diff = abs(x2 - x1);
            double midX = (x1 + x2) / 2.0;
            double midY = (y1 + y2) / 2.0;
            if (x1 == x2) {
                x1 += 0.0005;
            }
            double k = (y1 - y2) / (x1 - x2);
            double kPer = -1.0 / k;
            double bPer = midY + midX / k;
            double x1Per = midX + diff / 5, x2Per = midX - diff / 5;
            double y1Per = bPer + kPer * x1Per, y2Per = bPer + kPer * x2Per;
            currentState.add(new Point(x1Per, y1Per));
            currentState.add(new Point(x2Per, y2Per));
        }
    }

    private List<Point> getInitialState(CenterInputDTO centerInputDTO) {
        List<Point> initialState = new ArrayList<>();
        List<Future<RouteOutputDTO>> futureRouteResultList = new ArrayList<>();
        for (int i = 0; i < centerInputDTO.getUsers().size() - 1; i++) {
            for (int j = i + 1; j < centerInputDTO.getUsers().size(); j++) {
                RouteInputDTO routeInputDTO = new RouteInputDTO();
                routeInputDTO.setOrigin(centerInputDTO.getUsers().get(i).getLocation());
                routeInputDTO.setDestination(centerInputDTO.getUsers().get(j).getLocation());
                routeInputDTO.setMode(centerInputDTO.getUsers().get(i).getMode());
                routeInputDTO.setIncludeFerries(centerInputDTO.getUsers().get(i).getIncludeFerries());
                routeInputDTO.setIncludeHighways(centerInputDTO.getUsers().get(i).getIncludeHighways());
                routeInputDTO.setIncludeTolls(centerInputDTO.getUsers().get(i).getIncludeTolls());
                futureRouteResultList.add(routeService.getRouteAsync(routeInputDTO));
            }
        }
        for (Future<RouteOutputDTO> routeOutputDTOFuture : futureRouteResultList) {
            try {
                RouteOutputDTO routeOutputDTO = routeOutputDTOFuture.get();
                SummaryDTO summaryDTO = routeOutputDTO.getSummary();
                initialState.add(new Point(summaryDTO.getMinLat(), summaryDTO.getMinLon()));
                initialState.add(new Point(summaryDTO.getMaxLat(), summaryDTO.getMaxLon()));
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error(e.getMessage(), e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
        return initialState;
    }

    private List<Integer> getTimes(MatrixOutputValhallaModel matrixOutputValhallaModel) {
        List<SourceToTargetValhallaModel> sourceToTargets = matrixOutputValhallaModel.getSourcesToTargets().get(0);
        return sourceToTargets.stream()
                .map(SourceToTargetValhallaModel::getTime).collect(Collectors.toList());
    }

    private MatrixInputValhallaModel buildMatrixInput(LocationValhallaModel source, List<LocationValhallaModel> targets, String costing, CostingOptionsValhallaModel costingOptionsValhallaModel) {
        MatrixInputValhallaModel matrixInputValhallaModel = new MatrixInputValhallaModel();

        matrixInputValhallaModel.setSources(Collections.singletonList(source));
        matrixInputValhallaModel.setTargets(targets);
        matrixInputValhallaModel.setCosting(costing);
        matrixInputValhallaModel.setCostingOptionsValhallaModel(costingOptionsValhallaModel);

        return matrixInputValhallaModel;
    }
}