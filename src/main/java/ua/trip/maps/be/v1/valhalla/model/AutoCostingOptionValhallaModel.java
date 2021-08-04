package ua.trip.maps.be.v1.valhalla.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AutoCostingOptionValhallaModel {
    @JsonProperty("use_tolls")
    private Integer useTolls;

    @JsonProperty("toll_booth_penalty")
    private Integer tollBoothPenalty;

    @JsonProperty("use_highways")
    private Integer useHighways;

    @JsonProperty("use_ferry")
    private Integer useFerry;
}
