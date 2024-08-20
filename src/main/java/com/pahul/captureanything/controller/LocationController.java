package com.pahul.captureanything.controller;

import com.pahul.captureanything.model.Location;
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

    @RequestMapping(value = "/getSelectedCoordinates",method = RequestMethod.GET, produces ="application/json")
    public List<Location> getSelectedCoordinates(@RequestParam("address") String address )
    {
        return locationService.getSelectedLocation(address);
    }

//    @RequestMapping(value = "/getCoordinates",method = RequestMethod.GET, produces ="application/json")
//    public List<Location> getSelectedCoordinates(@RequestParam("city") String city, @RequestParam("country") String country )
//    {
//        return locationService.getSelectedLocation(city, state);
//    }

}
