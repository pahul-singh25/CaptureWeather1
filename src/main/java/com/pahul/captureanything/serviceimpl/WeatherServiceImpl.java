package com.pahul.captureanything.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pahul.captureanything.manager.ExternalCallManager;
import com.pahul.captureanything.model.Weather;
import com.pahul.captureanything.model.WeatherData;
import com.pahul.captureanything.mongo.repositories.LocationRepository;
import com.pahul.captureanything.mongo.repositories.WeatherRepository;
import com.pahul.captureanything.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private final ExternalCallManager externalCallManager;
    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository, LocationRepository locationRepository, ExternalCallManager externalCallManager, MongoTemplate mongoTemplate) {
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
        this.externalCallManager = externalCallManager;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Weather getCurrentWeather(String address) throws JsonProcessingException {

        try {
            WeatherData weatherData = externalCallManager.getWeatherInfo(address);

//        String weatherJson = (new ObjectMapper()).writeValueAsString(externalCallManager.getWeatherInfo(address));
            // Send the JSON data to Kafka
            if (weatherData != null) {
                Weather weather = new Weather(weatherData);
                weatherRepository.insert(weather);
                locationRepository.save(weatherData.getLocation());
                return weather;
            } else {
                LOGGER.error("weather data is empty");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching weather data: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public WeatherData getCurrentWeatherData(String address) throws JsonProcessingException {

        try {
            WeatherData weatherData = externalCallManager.getWeatherInfo(address);

//        String weatherJson = (new ObjectMapper()).writeValueAsString(externalCallManager.getWeatherInfo(address));
            // Send the JSON data to Kafka
            if (weatherData != null) {
                return weatherData;
            } else {
                LOGGER.error("Weather data is empty");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching weather data: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Weather> getWeather(String address, LocalDateTime datetime) {
        Query query = new Query();
        query.addCriteria(Criteria.where("dateTimeField").regex("\\d{4}-\\d{2}-\\d{2}"));
        return mongoTemplate.find(query, Weather.class);
    }

}

