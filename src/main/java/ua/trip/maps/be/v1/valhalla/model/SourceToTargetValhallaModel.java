package ua.trip.maps.be.v1.valhalla.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SourceToTargetValhallaModel {
    @JsonProperty("distance")
    private Double distance;

    @JsonProperty("time")
    private Integer time;

    @JsonProperty("to_index")
    private Integer toIndex;

    @JsonProperty("from_index")
    private Integer fromIndex;
}
