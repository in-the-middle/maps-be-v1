package ua.trip.maps.be.v1.valhalla.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RouteOutputValhallaModel {
    @JsonProperty("trip")
    TripValhallaModel tripValhallaModel;
}
