package ua.trip.maps.be.v1.valhalla.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MatrixInputValhallaModel {
    @JsonProperty("sources")
    private List<LocationValhallaModel> sources;

    @JsonProperty("targets")
    private List<LocationValhallaModel> targets;

    @JsonProperty("costing")
    private String costing;

    @JsonProperty("units")
    private String units;
}
