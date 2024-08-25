package com.pahul.captureanything.controller;

import com.pahul.captureanything.model.WeatherData;
import com.pahul.captureanything.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "/getWeather",method = RequestMethod.GET, produces ="application/json")
    public WeatherData getWeather(@RequestParam("address") String address ) throws Exception
    {
        return weatherService.getCurrentWeather(address);
    }
}
