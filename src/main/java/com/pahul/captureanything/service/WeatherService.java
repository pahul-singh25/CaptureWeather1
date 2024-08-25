package com.pahul.captureanything.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pahul.captureanything.model.Weather;
import com.pahul.captureanything.model.WeatherData;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherService {
    Weather getCurrentWeather(String address) throws JsonProcessingException;

    WeatherData getCurrentWeatherData(String address) throws JsonProcessingException;

    List<Weather> getWeather(String address, LocalDateTime datetime);
}
