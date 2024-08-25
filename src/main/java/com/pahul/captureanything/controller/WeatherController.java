package com.pahul.captureanything.controller;

import com.pahul.captureanything.model.Weather;
import com.pahul.captureanything.service.WeatherService;
import com.pahul.captureanything.utils.DateCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/getWeather",method = RequestMethod.GET, produces ="application/json")
    public Weather getWeather(@RequestParam("address") String address ) throws Exception {
        return weatherService.getCurrentWeather(address);
    }

    @RequestMapping(value = "/getWeatherByDate",method = RequestMethod.GET, produces ="application/json")
    public List<Weather> getWeatherByDate(@RequestParam("address") String address, @RequestParam("when") String when) throws Exception {
        LocalDateTime datetime = LocalDateTime.of(DateCalculator.calculateDate(when), LocalTime.now());
        return weatherService.getWeather(address,datetime);
    }

}
