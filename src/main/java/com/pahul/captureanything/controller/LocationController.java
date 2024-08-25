package com.pahul.captureanything.controller;

import com.pahul.captureanything.manager.kafka.WeatherTopicProducer;
import com.pahul.captureanything.model.Location;
import com.pahul.captureanything.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class LocationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

    private final LocationService locationService;
    private final WeatherTopicProducer weatherTopicProducer;

    @Autowired
    public LocationController(LocationService locationService, WeatherTopicProducer weatherTopicProducer) {
        this.locationService = locationService;
        this.weatherTopicProducer = weatherTopicProducer;
    }

    @RequestMapping(value = "/getSelectedCoordinates",method = RequestMethod.GET, produces ="application/json")
    public List<Location> getSelectedCoordinates(@RequestParam("address") String address )
    {
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            return locationService.getSelectedLocation(address, "");
        }finally {
            watch.stop();
            LOGGER.info("Time taken By /getSelectedCoordinates :{} ", watch.getTotalTimeMillis() + " ms");
        }
    }

    @RequestMapping(value = "/getWeather",method = RequestMethod.GET, produces ="application/json")
    public String getSelectGetCurrentWeather(@RequestParam("address") String address) throws Exception {
        return weatherTopicProducer.currentWeatherProducer(address);
    }

}
