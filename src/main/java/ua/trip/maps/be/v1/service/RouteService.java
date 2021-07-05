package ua.trip.maps.be.v1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.trip.maps.be.v1.utils.MappingUtils;
import ua.trip.maps.be.v1.valhalla.consumer.ValhallaApiConsumer;
import ua.trip.maps.be.v1.valhalla.model.RouteInputValhallaModel;
import ua.trip.maps.be.v1.valhalla.model.RouteOutputValhallaModel;
import ua.trip.maps.service.model.RouteInputDTO;
import ua.trip.maps.service.model.RouteOutputDTO;

import java.util.concurrent.Future;

@Service
public class RouteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    ValhallaApiConsumer valhallaApiConsumer;

    public RouteOutputDTO getRoute(RouteInputDTO routeInputDTO) {
        RouteInputValhallaModel routeInputValhallaModel = MappingUtils.map(routeInputDTO);
        ResponseEntity<RouteOutputValhallaModel> routeOutputValhallaModelResponseEntity = valhallaApiConsumer.getRoute(routeInputValhallaModel);
        if (routeOutputValhallaModelResponseEntity.getStatusCode().isError() || routeOutputValhallaModelResponseEntity.getBody() == null) {
            LOGGER.error("Valhalla route service answered with exception: status code={}, response body={}", routeOutputValhallaModelResponseEntity.getStatusCode(), routeOutputValhallaModelResponseEntity.getBody());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Route service answered with exception");
        }
        RouteOutputValhallaModel routeOutputValhallaModel = routeOutputValhallaModelResponseEntity.getBody();
        return MappingUtils.map(routeOutputValhallaModel);
    }

    @Async
    public Future<RouteOutputDTO> getRouteAsync(RouteInputDTO routeInputDTO) {
        RouteInputValhallaModel routeInputValhallaModel = MappingUtils.map(routeInputDTO);
        ResponseEntity<RouteOutputValhallaModel> routeOutputValhallaModelResponseEntity = valhallaApiConsumer.getRoute(routeInputValhallaModel);
        if (routeOutputValhallaModelResponseEntity.getStatusCode().isError() || routeOutputValhallaModelResponseEntity.getBody() == null) {
            LOGGER.error("Valhalla route service answered with exception: status code={}, response body={}", routeOutputValhallaModelResponseEntity.getStatusCode(), routeOutputValhallaModelResponseEntity.getBody());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Route service answered with exception");
        }
        RouteOutputValhallaModel routeOutputValhallaModel = routeOutputValhallaModelResponseEntity.getBody();
        return new AsyncResult<>(MappingUtils.map(routeOutputValhallaModel));
    }
}
