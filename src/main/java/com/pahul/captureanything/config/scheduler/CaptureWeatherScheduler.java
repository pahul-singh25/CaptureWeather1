package com.pahul.captureanything.config.scheduler;

import com.pahul.captureanything.manager.kafka.WeatherTopicConsumer;
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
public class CaptureWeatherScheduler {
    @Autowired
    private WeatherTopicConsumer weatherTopicConsumer;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherTopicProducer weatherTopicProducer;

//    @Scheduled(fixedRate = 120000) // 120000 milliseconds = 2 minutes
//    public void runWeatherConsumer() {
//        try {
//            weatherTopicConsumer.currentWeatherConsumer();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Scheduled(fixedRate = 1000)
    public void fetchWeatherForUsers() throws Exception
    {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if(user.getPreference()!=null)
            {
                for (String address : user.getPreference())
                {
                    WeatherData weatherData = weatherService.getCurrentWeather(address);
                    if(weatherData!=null)
                    {
                        weatherTopicProducer.sendWeatherData(address,weatherData);
                    }

                }
            }

        }
    }


}
