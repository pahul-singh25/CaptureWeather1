package com.pahul.captureanything.serviceimpl;

import com.pahul.captureanything.model.Location;
import com.pahul.captureanything.repositories.LocationRepository;
import com.pahul.captureanything.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImplementation implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Location> getSelectedLocation(String city, String country) {
        return locationRepository.findByCityAndCountry(city,country);
    }
}
