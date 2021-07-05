package ua.trip.maps.service.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.trip.maps.service.model.LocationDTO;
import ua.trip.maps.service.model.TravelModeDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Object that represents input of route request
 */
@ApiModel(description = "Object that represents input of route request")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-05T08:11:52.488171+03:00[Europe/Zaporozhye]")

public class RouteInputDTO   {
  @JsonProperty("origin")
  private LocationDTO origin;

  @JsonProperty("destination")
  private LocationDTO destination;

  @JsonProperty("mode")
  private TravelModeDTO mode;

  @JsonProperty("includeTolls")
  private Boolean includeTolls;

  @JsonProperty("includeHighways")
  private Boolean includeHighways;

  @JsonProperty("includeFerries")
  private Boolean includeFerries;

  public RouteInputDTO origin(LocationDTO origin) {
    this.origin = origin;
    return this;
  }

  /**
   * Get origin
   * @return origin
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocationDTO getOrigin() {
    return origin;
  }

  public void setOrigin(LocationDTO origin) {
    this.origin = origin;
  }

  public RouteInputDTO destination(LocationDTO destination) {
    this.destination = destination;
    return this;
  }

  /**
   * Get destination
   * @return destination
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocationDTO getDestination() {
    return destination;
  }

  public void setDestination(LocationDTO destination) {
    this.destination = destination;
  }

  public RouteInputDTO mode(TravelModeDTO mode) {
    this.mode = mode;
    return this;
  }

  /**
   * Get mode
   * @return mode
  */
  @ApiModelProperty(value = "")

  @Valid

  public TravelModeDTO getMode() {
    return mode;
  }

  public void setMode(TravelModeDTO mode) {
    this.mode = mode;
  }

  public RouteInputDTO includeTolls(Boolean includeTolls) {
    this.includeTolls = includeTolls;
    return this;
  }

  /**
   * Get includeTolls
   * @return includeTolls
  */
  @ApiModelProperty(value = "")


  public Boolean getIncludeTolls() {
    return includeTolls;
  }

  public void setIncludeTolls(Boolean includeTolls) {
    this.includeTolls = includeTolls;
  }

  public RouteInputDTO includeHighways(Boolean includeHighways) {
    this.includeHighways = includeHighways;
    return this;
  }

  /**
   * Get includeHighways
   * @return includeHighways
  */
  @ApiModelProperty(value = "")


  public Boolean getIncludeHighways() {
    return includeHighways;
  }

  public void setIncludeHighways(Boolean includeHighways) {
    this.includeHighways = includeHighways;
  }

  public RouteInputDTO includeFerries(Boolean includeFerries) {
    this.includeFerries = includeFerries;
    return this;
  }

  /**
   * Get includeFerries
   * @return includeFerries
  */
  @ApiModelProperty(value = "")


  public Boolean getIncludeFerries() {
    return includeFerries;
  }

  public void setIncludeFerries(Boolean includeFerries) {
    this.includeFerries = includeFerries;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RouteInputDTO routeInputDTO = (RouteInputDTO) o;
    return Objects.equals(this.origin, routeInputDTO.origin) &&
        Objects.equals(this.destination, routeInputDTO.destination) &&
        Objects.equals(this.mode, routeInputDTO.mode) &&
        Objects.equals(this.includeTolls, routeInputDTO.includeTolls) &&
        Objects.equals(this.includeHighways, routeInputDTO.includeHighways) &&
        Objects.equals(this.includeFerries, routeInputDTO.includeFerries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(origin, destination, mode, includeTolls, includeHighways, includeFerries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RouteInputDTO {\n");
    
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
    sb.append("    destination: ").append(toIndentedString(destination)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    includeTolls: ").append(toIndentedString(includeTolls)).append("\n");
    sb.append("    includeHighways: ").append(toIndentedString(includeHighways)).append("\n");
    sb.append("    includeFerries: ").append(toIndentedString(includeFerries)).append("\n");
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

