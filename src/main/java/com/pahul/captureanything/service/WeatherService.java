package com.pahul.captureanything.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pahul.captureanything.model.WeatherData;

public interface WeatherService {
    WeatherData getCurrentWeather(String address) throws JsonProcessingException;
}
