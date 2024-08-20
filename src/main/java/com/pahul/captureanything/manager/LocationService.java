package com.pahul.captureanything.manager;

import com.pahul.captureanything.model.LocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class LocationService {

    @Autowired
    private final RestTemplate restTemplate;

    public LocationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public LocationResponse[] getLocationInfo(String cityName, String countryName) {
        String url = "https://nominatim.openstreetmap.org/search";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("q", cityName + "," + countryName)
            .queryParam("format", "json");

        return restTemplate.getForObject(uriBuilder.toUriString(), LocationResponse[].class);
    }
}
