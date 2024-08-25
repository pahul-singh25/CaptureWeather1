package com.pahul.captureanything.config.scheduler;

import com.pahul.captureanything.manager.kafka.WeatherTopicProducer;
import com.pahul.captureanything.model.User;
import com.pahul.captureanything.model.WeatherData;
import com.pahul.captureanything.repositories.UserRepository;
import com.pahul.captureanything.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProducerScheduler {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherTopicProducer weatherTopicProducer;

    @Scheduled(fixedRate = 20000)
    public void fetchWeatherForUsers() throws Exception
    {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            for (String address : user.getPreference()) {
                WeatherData weatherData = weatherService.getCurrentWeather(address);
                weatherTopicProducer.sendWeatherData(address,weatherData);
            }
        }
    }

}
