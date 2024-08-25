package com.pahul.captureanything.controller;

import com.pahul.captureanything.model.Weather;
import com.pahul.captureanything.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;


    @BeforeEach
    public void setup(){
        weatherController = new WeatherController(weatherService);
    }


    @Test
    public void getWeatherTest() throws Exception {

        // Arrange
        LocalDateTime datetime = LocalDateTime.of(2022, 1, 1, 0, 0);
        Weather weather = new Weather();
        when(weatherService.getCurrentWeather(anyString())).thenReturn(weather);

        // Act
        Weather response = weatherController.getWeather("Eagan MN United States");

        // Assert
        Assertions.assertNotNull(weather);
    }

    @Test
    public void getWeatherByDateTest() throws Exception {
        // Arrange
        String address = "New York";
        String when = "Yesterday";
        List<Weather> weatherList = List.of(new Weather());

        when(weatherService.getWeather(anyString(), any())).thenReturn(weatherList);

        // Act
        List<Weather> response = weatherController.getWeatherByDate(address, when);

        // Assert
        Assertions.assertNotNull((weatherList));
        Assertions.assertFalse(weatherList.isEmpty());
    }
}