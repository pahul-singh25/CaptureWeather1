package com.pahul.captureanything.service;

import com.pahul.captureanything.model.OldLocation;

import java.util.List;

public interface LocationService {


    List<OldLocation> getSelectedLocation(String address);

//    List<OldLocation> getSelectedLocation(String city, String country);
}
