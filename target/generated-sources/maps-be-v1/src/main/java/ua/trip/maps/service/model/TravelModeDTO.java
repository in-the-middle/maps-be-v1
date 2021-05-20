package ua.trip.maps.service.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Travel modes:  * Driving (default) - indicates standard driving directions using the road network  * Walking - requests walking directions via pedestrian paths & sidewalks (where available)  * Bicycling - requests bicycling directions via bicycle paths & preferred streets (where available)  * Transit - requests directions via public transit routes (where available) 
 */
public enum TravelModeDTO {
  
  DRIVING("DRIVING"),
  
  WALKING("WALKING"),
  
  BICYCLING("BICYCLING"),
  
  TRANSIT("TRANSIT");

  private String value;

  TravelModeDTO(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TravelModeDTO fromValue(String value) {
    for (TravelModeDTO b : TravelModeDTO.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

