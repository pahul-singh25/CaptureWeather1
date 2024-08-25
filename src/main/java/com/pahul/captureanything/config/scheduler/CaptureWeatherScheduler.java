package com.pahul.captureanything.config.scheduler;

import com.pahul.captureanything.manager.kafka.WeatherTopicConsumer;
import com.pahul.captureanything.manager.kafka.WeatherTopicProducer;
import com.pahul.captureanything.mongo.repositories.UserRepository;
import com.pahul.captureanything.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaptureWeatherScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureWeatherScheduler.class);
    private final WeatherTopicConsumer weatherTopicConsumer;
    private final UserRepository userRepository;
    private final WeatherService weatherService;
    private final WeatherTopicProducer weatherTopicProducer;

    @Autowired
    public CaptureWeatherScheduler(WeatherTopicConsumer weatherTopicConsumer, UserRepository userRepository, WeatherService weatherService, WeatherTopicProducer weatherTopicProducer) {
        this.weatherTopicConsumer = weatherTopicConsumer;
        this.userRepository = userRepository;
        this.weatherService = weatherService;
        this.weatherTopicProducer = weatherTopicProducer;
    }

//    @Scheduled(fixedRate = 120000) // 120000 milliseconds = 2 minutes
//    public void runWeatherConsumer() {
//        try {
//            weatherTopicConsumer.currentWeatherConsumer();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Scheduled(fixedRate = 1000)
//    public void fetchWeatherForUsers() throws JsonProcessingException {
//        List<User> users = userRepository.findAll();
//        users.stream()
//                .filter(user -> !user.getPreference().isEmpty())
//                .forEach(user -> {
//                    // loop over the user's preferences here
//                    user.getPreference().forEach(address -> {
//                        WeatherData weatherData = null;
//                        try {
//                            weatherData = weatherService.getCurrentWeatherData(address);
//                            if (weatherData != null) {
//                                weatherTopicProducer.sendWeatherData(address, weatherData);
//                            }
//                        } catch (JsonProcessingException e) {
//                            LOGGER.error(e.getMessage(), e);
//                        }
//
//                    });
//                });
//    }

}
