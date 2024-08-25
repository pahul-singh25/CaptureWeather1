package com.pahul.captureanything.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize
public class WeatherData {
     //We will be weather.current.toString() into this.
    private Location location;
    private Current current;
}
