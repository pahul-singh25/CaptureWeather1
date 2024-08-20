package com.pahul.captureanything.manager;

import com.google.common.collect.Lists;
import com.pahul.captureanything.model.LocationResponse;
import com.pahul.captureanything.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExternalCallManager {

    private RestTemplate restTemplate;

    public ExternalCallManager(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     *
     * @param address
     * @return
     */

    public List<LocationResponse> getLocationInfo(String address) {
        String baseurl = "http://nominatim.openstreetmap.org/search";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseurl)
                .queryParam("q", address)
                .queryParam("format", "json");
        String url =uriBuilder.toUriString();

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Referer", "strict-origin-when-cross-origin");

        // Create the HTTP entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the GET request and parse the response
        List response = restTemplate.getForObject(
                url,
               List.class
        );
        List<LocationResponse> result = Lists.newArrayList();
        response.forEach(o->{
                LocationResponse obj = LocationResponse.mapToLocationResponse((LinkedHashMap) o);
                result.add(obj);
            });

        return result;
    }

    public WeatherData getWeatherInfo(String address) {
//        String baseurl = "http://nominatim.openstreetmap.org/search";
        String baseurl ="https://api.weatherapi.com/v1/current.json";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseurl)
                .queryParam("q", address)
                .queryParam("key", "c1d160e3e20a45c8bd544921242008");
        String url =uriBuilder.toUriString();
        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Referer", "strict-origin-when-cross-origin");

        // Create the HTTP entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the GET request and parse the response
        ResponseEntity<WeatherData> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                WeatherData.class
        );

        return response.getBody();
    }
}


