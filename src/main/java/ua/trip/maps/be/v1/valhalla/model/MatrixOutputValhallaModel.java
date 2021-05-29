package ua.trip.maps.be.v1.valhalla.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MatrixOutputValhallaModel {
    @JsonProperty("sources")
    private List<List<LocationValhallaModel>> sources;

    @JsonProperty("targets")
    private List<List<LocationValhallaModel>> targets;

    @JsonProperty("sources_to_targets")
    private List<List<SourceToTargetValhallaModel>> sourcesToTargets;
}
