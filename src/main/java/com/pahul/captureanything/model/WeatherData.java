package com.pahul.captureanything.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@Data
public class WeatherData {
//    @JsonProperty("OldLocation")
    private Location location;
    private Current current;
}
