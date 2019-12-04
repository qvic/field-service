package com.ips42.fieldservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CoordinateDto {

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("long")
    private double longitude;
}
