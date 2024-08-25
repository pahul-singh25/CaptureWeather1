package com.pahul.captureanything.manager;

import com.pahul.captureanything.model.WeatherData;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ExternalCallManager {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExternalCallManager.class);
    private final RestTemplate restTemplate;

    public ExternalCallManager(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
        try {
            // Make the GET request and parse the response
            ResponseEntity<WeatherData> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    WeatherData.class
            );
            return response.getBody();
        } catch (Exception e) {
            // Handle the exception (log it, return a default value, etc.)
            LOGGER.error("Error: {}", e.getMessage());
            return null;
        }

    }
}


