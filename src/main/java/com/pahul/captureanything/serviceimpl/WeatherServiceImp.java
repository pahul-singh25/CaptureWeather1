package com.pahul.captureanything.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pahul.captureanything.manager.ExternalCallManager;
import com.pahul.captureanything.model.WeatherData;
import com.pahul.captureanything.mongo.repositories.WeatherRepository;
import com.pahul.captureanything.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class WeatherServiceImp implements WeatherService {

    @Autowired
    ExternalCallManager externalCallManager;
    @Autowired
    WeatherRepository weatherRepository;


    @Override
    public WeatherData getCurrentWeather(String address) throws JsonProcessingException {


        var props = new Properties();
        props.put("bootstrap.servers", "https://epic-mink-5018-us1-kafka.upstash.io:9092");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ZXBpYy1taW5rLTUwMTgkmWwXA4TDCl9AukmZYBcZbR9s9ckR3aZAg8R2a6NWD6s\" password=\"YTBhZTliM2ItMWM5MC00ZjlhLWE5NmYtZDc2Y2UwNzU1Yjgw\";");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        WeatherData weatherData=externalCallManager.getWeatherInfo(address);

//        String weatherJson = (new ObjectMapper()).writeValueAsString(externalCallManager.getWeatherInfo(address));
            // Send the JSON data to Kafka

        weatherRepository.insert(weatherData);

        return weatherData;
    }



}

