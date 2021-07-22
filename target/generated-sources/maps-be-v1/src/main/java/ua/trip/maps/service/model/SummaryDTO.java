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
 * SummaryDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-22T20:12:05.839217+03:00[Europe/Zaporozhye]")

public class SummaryDTO   {
  @JsonProperty("minLat")
  private Double minLat = null;

  @JsonProperty("minLon")
  private Double minLon = null;

  @JsonProperty("maxLat")
  private Double maxLat = null;

  @JsonProperty("maxLon")
  private Double maxLon = null;

  @JsonProperty("time")
  private Double time = null;

  @JsonProperty("length")
  private Double length = null;

  public SummaryDTO minLat(Double minLat) {
    this.minLat = minLat;
    return this;
  }

  /**
   * Get minLat
   * @return minLat
  */
  @ApiModelProperty(value = "")


  public Double getMinLat() {
    return minLat;
  }

  public void setMinLat(Double minLat) {
    this.minLat = minLat;
  }

  public SummaryDTO minLon(Double minLon) {
    this.minLon = minLon;
    return this;
  }

  /**
   * Get minLon
   * @return minLon
  */
  @ApiModelProperty(value = "")


  public Double getMinLon() {
    return minLon;
  }

  public void setMinLon(Double minLon) {
    this.minLon = minLon;
  }

  public SummaryDTO maxLat(Double maxLat) {
    this.maxLat = maxLat;
    return this;
  }

  /**
   * Get maxLat
   * @return maxLat
  */
  @ApiModelProperty(value = "")


  public Double getMaxLat() {
    return maxLat;
  }

  public void setMaxLat(Double maxLat) {
    this.maxLat = maxLat;
  }

  public SummaryDTO maxLon(Double maxLon) {
    this.maxLon = maxLon;
    return this;
  }

  /**
   * Get maxLon
   * @return maxLon
  */
  @ApiModelProperty(value = "")


  public Double getMaxLon() {
    return maxLon;
  }

  public void setMaxLon(Double maxLon) {
    this.maxLon = maxLon;
  }

  public SummaryDTO time(Double time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
  */
  @ApiModelProperty(value = "")


  public Double getTime() {
    return time;
  }

  public void setTime(Double time) {
    this.time = time;
  }

  public SummaryDTO length(Double length) {
    this.length = length;
    return this;
  }

  /**
   * Get length
   * @return length
  */
  @ApiModelProperty(value = "")


  public Double getLength() {
    return length;
  }

  public void setLength(Double length) {
    this.length = length;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SummaryDTO summaryDTO = (SummaryDTO) o;
    return Objects.equals(this.minLat, summaryDTO.minLat) &&
        Objects.equals(this.minLon, summaryDTO.minLon) &&
        Objects.equals(this.maxLat, summaryDTO.maxLat) &&
        Objects.equals(this.maxLon, summaryDTO.maxLon) &&
        Objects.equals(this.time, summaryDTO.time) &&
        Objects.equals(this.length, summaryDTO.length);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minLat, minLon, maxLat, maxLon, time, length);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SummaryDTO {\n");
    
    sb.append("    minLat: ").append(toIndentedString(minLat)).append("\n");
    sb.append("    minLon: ").append(toIndentedString(minLon)).append("\n");
    sb.append("    maxLat: ").append(toIndentedString(maxLat)).append("\n");
    sb.append("    maxLon: ").append(toIndentedString(maxLon)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
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

