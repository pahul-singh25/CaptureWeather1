package com.pahul.captureanything.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Weather")
@Data
public class Weather {
    @Id
    private Long id;

    @Column(name = "WEATHER_ID",  unique = true)
    private String weatherDataId;
    
    private LocalDateTime dateTime;

    //We will be weather.current.toString() in thie
    private String weather;

}
