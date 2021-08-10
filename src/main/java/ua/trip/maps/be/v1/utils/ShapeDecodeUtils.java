package ua.trip.maps.be.v1.utils;

import ua.trip.maps.service.model.LocationDTO;

import java.util.ArrayList;
import java.util.List;

public class ShapeDecodeUtils {
    private static final double kPolylinePrecision = 1E6;

    private static final double kInvPolylinePrecision = 1.0 / kPolylinePrecision;

    public static List<LocationDTO> decode(String shape){
        List<LocationDTO> coordinates = new ArrayList<>();
        int index = 0, lat = 0, lng = 0, shift = 0, result = 0, latitude_change, longitude_change;
        // Coordinates have variable length when encoded, so just keep
        // track of whether we've hit the end of the string. In each
        // loop iteration, a single coordinate is decoded.
        while (index < shape.length()) {

            // Reset shift, result, and byte
            int bte;
            shift = 0;
            result = 0;

            do {
                bte = ((int) shape.charAt(index++)) - 63;
                result |= (bte & 0x1f) << shift;
                shift += 5;
            } while (bte >= 0x20);
            latitude_change = ((result & 1) > 0 ? ~(result >> 1) : (result >> 1));

            shift = result = 0;

            do {
                bte = ((int) shape.charAt(index++)) - 63;
                result |= (bte & 0x1f) << shift;
                shift += 5;
            } while (bte >= 0x20);

            longitude_change = ((result & 1) > 0 ? ~(result >> 1) : (result >> 1));

            lat += latitude_change;
            lng += longitude_change;

            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLat(lat * kInvPolylinePrecision);
            locationDTO.setLon(lng * kInvPolylinePrecision);
            coordinates.add(locationDTO);
        }

        return coordinates;
    }
}
