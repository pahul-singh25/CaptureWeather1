package com.pahul.captureanything.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@Data
public class WeatherData {
    @JsonProperty("Location")
    private NewLocation location;
    private Current current;
}
