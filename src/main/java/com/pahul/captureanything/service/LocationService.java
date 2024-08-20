package com.pahul.captureanything.service;

import com.pahul.captureanything.model.Location;

import java.util.List;

public interface LocationService {


    List<Location> getSelectedLocation(String address);

//    List<Location> getSelectedLocation(String city, String country);
}
