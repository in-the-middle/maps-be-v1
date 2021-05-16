package ua.trip.maps.be.v1.valhalla.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SummaryValhallaModel {
    @JsonProperty("has_time_restrictions")
    private Boolean hasTimeRestrictions;

    @JsonProperty("min_lat")
    private Double minLat;

    @JsonProperty("min_lon")
    private Double minLon;

    @JsonProperty("max_lat")
    private Double maxLat;

    @JsonProperty("max_lon")
    private Double maxLon;

    @JsonProperty("time")
    private Double time;

    @JsonProperty("length")
    private Double length;

    @JsonProperty("cost")
    private Double cost;
}
