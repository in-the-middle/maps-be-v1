package ua.trip.maps.be.v1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.trip.maps.be.v1.geometry.service.PolygonPointGenerator;
import ua.trip.maps.service.model.CenterInputDTO;
import ua.trip.maps.service.model.CenterOutputDTO;
import ua.trip.maps.service.model.LocationDTO;
import ua.trip.maps.service.model.UserInfoDTO;


@Service
public class CenterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CenterService.class);

    @Autowired
    PolygonPointGenerator polygonPointGenerator;

    public CenterOutputDTO getCenter(CenterInputDTO centerInputDTO) {
        double sumLat = 0;
        double sumLon = 0;
        for (UserInfoDTO user : centerInputDTO.getUsers()) {
            sumLat += user.getLocation().getLat();
            sumLon += user.getLocation().getLon();
        }
        CenterOutputDTO centerOutputDTO = new CenterOutputDTO();
        LocationDTO result = new LocationDTO();
        result.setLat(sumLat / centerInputDTO.getUsers().size());
        result.setLon(sumLon / centerInputDTO.getUsers().size());
        centerOutputDTO.setLocation(result);
        return centerOutputDTO;
    }
}