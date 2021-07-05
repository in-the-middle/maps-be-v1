package ua.trip.maps.be.v1.utils;

import ua.trip.maps.be.v1.valhalla.model.*;
import ua.trip.maps.service.model.*;

import java.util.ArrayList;
import java.util.List;

public class MappingUtils {
    public static RouteInputValhallaModel map(RouteInputDTO routeInputDTO) {
        RouteInputValhallaModel routeInputValhallaModel = new RouteInputValhallaModel();
        routeInputValhallaModel.setCosting(map(routeInputDTO.getMode()));
        //todo add unit in contract
        routeInputValhallaModel.setUnits("kilometers");
        //todo add destination type in contract
        routeInputValhallaModel.setDirectionsType("none");

        List<LocationValhallaModel> locations = new ArrayList<>();
        locations.add(map(routeInputDTO.getOrigin()));
        locations.add(map(routeInputDTO.getDestination()));
        routeInputValhallaModel.setLocations(locations);

        return routeInputValhallaModel;
    }

    public static RouteOutputDTO map(RouteOutputValhallaModel routeOutputValhallaModel) {
        RouteOutputDTO routeOutputDTO = new RouteOutputDTO();
        TripValhallaModel trip = routeOutputValhallaModel.getTripValhallaModel();
        List<LocationValhallaModel> locations = trip.getLocations();
        routeOutputDTO.setOrigin(map(locations.get(0)));
        routeOutputDTO.setDestination(map(locations.get(1)));
        routeOutputDTO.setUnits(trip.getUnits());
        routeOutputDTO.setLanguage(trip.getLanguage());
        routeOutputDTO.setShape(trip.getLegs().get(0).getShape());
        routeOutputDTO.setSummary(map(trip.getSummary()));
        return routeOutputDTO;
    }

    public static String map(TravelModeDTO travelModeDTO) {
        if (travelModeDTO.equals(TravelModeDTO.DRIVING)) {
            return "auto";
        } else if (travelModeDTO.equals(TravelModeDTO.BICYCLING)) {
            return "bicycle";
        } else if (travelModeDTO.equals(TravelModeDTO.WALKING)) {
            return "pedestrian";
        } else if (travelModeDTO.equals(TravelModeDTO.TRANSIT)) {
            //todo transit service integration
        }
        return "auto";
    }

    private static LocationValhallaModel map(LocationDTO locationDTO) {
        LocationValhallaModel locationValhallaModel = new LocationValhallaModel();
        locationValhallaModel.setLat(locationDTO.getLat());
        locationValhallaModel.setLon(locationDTO.getLon());
        return locationValhallaModel;
    }

    private static LocationDTO map(LocationValhallaModel locationValhallaModel) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLat(locationValhallaModel.getLat());
        locationDTO.setLon(locationValhallaModel.getLon());
        return locationDTO;
    }

    private static SummaryDTO map(SummaryValhallaModel summaryValhallaModel) {
        SummaryDTO summaryDTO = new SummaryDTO();
        summaryDTO.setLength(summaryValhallaModel.getLength());
        summaryDTO.setMaxLat(summaryValhallaModel.getMaxLat());
        summaryDTO.setMaxLon(summaryValhallaModel.getMaxLon());
        summaryDTO.setMinLat(summaryValhallaModel.getMinLat());
        summaryDTO.setMinLon(summaryValhallaModel.getMinLon());
        summaryDTO.setTime(summaryValhallaModel.getTime());
        return summaryDTO;
    }
}
