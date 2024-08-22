package com.pahul.captureanything.config.scheduler;

import com.pahul.captureanything.manager.kafka.WeatherTopicConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CaptureWeatherScheduler {
    @Autowired
    private WeatherTopicConsumer weatherTopicConsumer;

    @Scheduled(fixedRate = 120000) // 120000 milliseconds = 2 minutes
    public void runWeatherConsumer() {
        try {
            weatherTopicConsumer.currentWeatherConsumer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
