package ua.trip.maps.service.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Single set of lat and lng fields
 */
@ApiModel(description = "Single set of lat and lng fields")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-05T08:11:52.488171+03:00[Europe/Zaporozhye]")

public class LocationDTO   {
  @JsonProperty("lat")
  private Double lat = null;

  @JsonProperty("lon")
  private Double lon = null;

  public LocationDTO lat(Double lat) {
    this.lat = lat;
    return this;
  }

  /**
   * Latitude
   * @return lat
  */
  @ApiModelProperty(value = "Latitude")


  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public LocationDTO lon(Double lon) {
    this.lon = lon;
    return this;
  }

  /**
   * Longitude
   * @return lon
  */
  @ApiModelProperty(value = "Longitude")


  public Double getLon() {
    return lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationDTO locationDTO = (LocationDTO) o;
    return Objects.equals(this.lat, locationDTO.lat) &&
        Objects.equals(this.lon, locationDTO.lon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lat, lon);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocationDTO {\n");
    
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

