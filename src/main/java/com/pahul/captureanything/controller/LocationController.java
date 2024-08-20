package com.pahul.captureanything.controller;

import com.pahul.captureanything.manager.kafka.WeatherTopicProducer;
import com.pahul.captureanything.model.Location;
import com.pahul.captureanything.model.WeatherData;
import com.pahul.captureanything.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    WeatherTopicProducer weatherTopicProducer;

    @RequestMapping(value = "/getSelectedCoordinates",method = RequestMethod.GET, produces ="application/json")
    public List<Location> getSelectedCoordinates(@RequestParam("address") String address )
    {
        return locationService.getSelectedLocation(address);
    }

    @RequestMapping(value = "/getWeather",method = RequestMethod.GET, produces ="application/json")
    public String getSelectegetCurrentWeather(@RequestParam("address") String address) throws Exception {
        return weatherTopicProducer.currentWeatherProducer(address);
    }

}
