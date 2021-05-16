package ua.trip.maps.be.v1.valhalla.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TripValhallaModel {
    @JsonProperty("locations")
    private List<LocationValhallaModel> locations;

    @JsonProperty("legs")
    private List<LegValhallaModel> legs;

    @JsonProperty("summary")
    private SummaryValhallaModel summary;

    @JsonProperty("status_message")
    private String statusMessage;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("units")
    private String units;

    @JsonProperty("language")
    private String language;
}
