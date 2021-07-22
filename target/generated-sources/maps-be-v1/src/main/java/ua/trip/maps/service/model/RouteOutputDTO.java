package ua.trip.maps.service.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.trip.maps.service.model.LocationDTO;
import ua.trip.maps.service.model.SummaryDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RouteOutputDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-22T20:12:05.839217+03:00[Europe/Zaporozhye]")

public class RouteOutputDTO   {
  @JsonProperty("units")
  private String units;

  @JsonProperty("language")
  private String language;

  @JsonProperty("origin")
  private LocationDTO origin;

  @JsonProperty("destination")
  private LocationDTO destination;

  @JsonProperty("summary")
  private SummaryDTO summary;

  @JsonProperty("shape")
  private String shape;

  public RouteOutputDTO units(String units) {
    this.units = units;
    return this;
  }

  /**
   * Get units
   * @return units
  */
  @ApiModelProperty(value = "")


  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

  public RouteOutputDTO language(String language) {
    this.language = language;
    return this;
  }

  /**
   * Get language
   * @return language
  */
  @ApiModelProperty(value = "")


  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public RouteOutputDTO origin(LocationDTO origin) {
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

  public RouteOutputDTO destination(LocationDTO destination) {
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

  public RouteOutputDTO summary(SummaryDTO summary) {
    this.summary = summary;
    return this;
  }

  /**
   * Get summary
   * @return summary
  */
  @ApiModelProperty(value = "")

  @Valid

  public SummaryDTO getSummary() {
    return summary;
  }

  public void setSummary(SummaryDTO summary) {
    this.summary = summary;
  }

  public RouteOutputDTO shape(String shape) {
    this.shape = shape;
    return this;
  }

  /**
   * Get shape
   * @return shape
  */
  @ApiModelProperty(value = "")


  public String getShape() {
    return shape;
  }

  public void setShape(String shape) {
    this.shape = shape;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RouteOutputDTO routeOutputDTO = (RouteOutputDTO) o;
    return Objects.equals(this.units, routeOutputDTO.units) &&
        Objects.equals(this.language, routeOutputDTO.language) &&
        Objects.equals(this.origin, routeOutputDTO.origin) &&
        Objects.equals(this.destination, routeOutputDTO.destination) &&
        Objects.equals(this.summary, routeOutputDTO.summary) &&
        Objects.equals(this.shape, routeOutputDTO.shape);
  }

  @Override
  public int hashCode() {
    return Objects.hash(units, language, origin, destination, summary, shape);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RouteOutputDTO {\n");
    
    sb.append("    units: ").append(toIndentedString(units)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
    sb.append("    destination: ").append(toIndentedString(destination)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    shape: ").append(toIndentedString(shape)).append("\n");
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

