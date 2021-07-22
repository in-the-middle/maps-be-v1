package ua.trip.maps.be.v1.valhalla.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RouteInputValhallaModel {
    @JsonProperty("locations")
    private List<LocationValhallaModel> locations;

    @JsonProperty("costing")
    private String costing;

    @JsonProperty("directions_type")
    private String directionsType;

    @JsonProperty("units")
    private String units;

    @JsonProperty("costing_options")
    CostingOptionsValhallaModel costingOptionsValhallaModel;

//    @JsonProperty("use_tolls")
//    private Integer useTolls;
//
//    @JsonProperty("use_highways")
//    private Integer useHighways;
//
//    @JsonProperty("use_ferry")
//    private Integer useFerry;
}
