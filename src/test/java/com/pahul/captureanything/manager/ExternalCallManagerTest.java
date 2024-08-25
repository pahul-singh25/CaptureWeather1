package com.pahul.captureanything.manager;

import com.pahul.captureanything.model.WeatherData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExternalCallManagerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExternalCallManager externalCallManager;

    @BeforeEach
    public void setup() {
        // Initialize the RestTemplate mock
        externalCallManager = new ExternalCallManager(restTemplate);
    }

    @Test
    public void testGetWeatherInfo_ValidAddress() {
        // Arrange
        String address = "New York";
        String baseUrl = "https://api.weatherapi.com/v1/current.json";
        WeatherData expectedResponse = new WeatherData();
        // Initialize the expected response with some sample data

        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(), any(Class.class)))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        // Act
        WeatherData actualResponse = externalCallManager.getWeatherInfo(address);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testGetWeatherInfo_InvalidAddress() {
        // Arrange
        String address = "Invalid Address";
        String baseUrl = "https://api.weatherapi.com/v1/current.json";

        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(), any(Class.class)))
                .thenThrow(HttpClientErrorException.class);

        // Act
        WeatherData actualResponse = externalCallManager.getWeatherInfo(address);

        // Assert
        assertNull(actualResponse);
    }

    @Test
    public void testGetWeatherInfo_NullAddress() {
        // Arrange
        String address = null;
        String baseUrl = "https://api.weatherapi.com/v1/current.json";

        // Act
        WeatherData actualResponse = externalCallManager.getWeatherInfo(address);

        // Assert
        assertNull(actualResponse);
    }


//        when(restTemplate.exchange(anyString(), ArgumentMatchers.<HttpMethod>any(HttpMethod.class), ArgumentMatchers.<HttpEntity>any(HttpEntity.class), eq(WeatherData.class))).thenReturn(ResponseEntity.ok(weather));




}