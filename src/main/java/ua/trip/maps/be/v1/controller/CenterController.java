package ua.trip.maps.be.v1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ua.trip.maps.be.v1.service.CenterService;
import ua.trip.maps.be.v1.service.RouteService;
import ua.trip.maps.be.v1.utils.ShapeDecodeUtils;
import ua.trip.maps.service.api.CenterApi;
import ua.trip.maps.service.model.*;

import java.util.List;

@RestController
public class CenterController implements CenterApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(CenterController.class);

    @Autowired
    CenterService centerService;

    @Autowired
    RouteService routeService;

    @Override
    public ResponseEntity<CenterOutputDTO> getCenter(CenterInputDTO centerInputDTO) {
        LOGGER.info("GET Center: numberOfUsers=[{}]", centerInputDTO.getUsers().size());
        CenterOutputDTO centerOutputDTO = centerService.getCenter(centerInputDTO);

        RouteInputDTO routeInputDTO = new RouteInputDTO();
        routeInputDTO.setMode(centerInputDTO.getUsers().get(0).getMode());
        routeInputDTO.setOrigin(centerInputDTO.getUsers().get(0).getLocation());
        routeInputDTO.setDestination(centerOutputDTO.getLocation());

        RouteOutputDTO routeOutputDTO = routeService.getRoute(routeInputDTO);
        List<LocationDTO> locationDTOList = ShapeDecodeUtils.decode(routeOutputDTO.getShape());
        centerOutputDTO.setLocation(locationDTOList.get(locationDTOList.size() - 1));
        return ResponseEntity.ok(centerOutputDTO);
    }
}
