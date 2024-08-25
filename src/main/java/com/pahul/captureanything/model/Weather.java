package com.pahul.captureanything.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonSerialize
@Data
public class Weather {

    public Weather() {
    }

    public Weather(WeatherData weatherData) {
        this.current = weatherData.getCurrent();
        this.locationId = weatherData.getLocation().createLocationId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateTime = LocalDateTime.parse(current.getLast_updated(), formatter);
    }

    @Id
    private String id;
    private LocalDateTime dateTime;
    //This will ont - to many with Location table
    //This locationId will be in the table/collection Location
    //Saving weather should be along with saving data of location
    @JsonProperty("locationId")
    private String locationId;

    //We will be weather.current.toString() into this.
    private Current current;




}
