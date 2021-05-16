package ua.trip.maps.be.v1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ua.trip.maps.be.v1.service.RouteService;
import ua.trip.maps.service.api.RouteApi;
import ua.trip.maps.service.model.RouteInputDTO;
import ua.trip.maps.service.model.RouteOutputDTO;


@RestController
public class RouteController implements RouteApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    RouteService routeService;

    @Override
    public ResponseEntity<RouteOutputDTO> getRoute(RouteInputDTO routeInputDTO) {
        return ResponseEntity.ok(routeService.getRoute(routeInputDTO));
    }
}
