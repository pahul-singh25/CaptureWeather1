package com.pahul.captureanything.service;

import com.pahul.captureanything.model.Location;

import java.util.List;

public interface LocationService {


    List<Location> getSelectedLocation(String name, String country);

    Location saveIfDoNotExist(Location location);
}
