package ua.trip.maps.be.v1.valhalla.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationValhallaModel {
    @JsonProperty("type")
    private String type;

    @JsonProperty("lat")
    private Double lat;

    @JsonProperty("lon")
    private Double lon;

    @JsonProperty("city")
    private String city;

    @JsonProperty("original_index")
    private Integer originalIndex;
}
