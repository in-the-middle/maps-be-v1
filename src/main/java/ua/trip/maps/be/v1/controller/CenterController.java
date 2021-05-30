package ua.trip.maps.be.v1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ua.trip.maps.be.v1.service.CenterService;
import ua.trip.maps.service.api.CenterApi;
import ua.trip.maps.service.model.CenterInputDTO;
import ua.trip.maps.service.model.CenterOutputDTO;

@RestController
public class CenterController implements CenterApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(CenterController.class);

    @Autowired
    CenterService centerService;

    @Override
    public ResponseEntity<CenterOutputDTO> getCenter(CenterInputDTO centerInputDTO) {
        return ResponseEntity.ok(centerService.getCenter(centerInputDTO));
    }
}
