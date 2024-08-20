package com.pahul.captureanything.manager;

import com.google.common.collect.Lists;
import com.pahul.captureanything.model.LocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.List;

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
        List<LocationResponse> result = Lists.newArrayList();
        String url = "http://nominatim.openstreetmap.org/search";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("q", address)
                .queryParam("format", "json");

        ResponseEntity<List> res = restTemplate.exchange(uriBuilder.toUriString(),HttpMethod.GET, null,  List.class);
        if (res.getStatusCode().is2xxSuccessful()) {
            List<LinkedHashMap> ll =(List<LinkedHashMap>) res.getBody();
            ll.forEach(lhm -> {
                LocationResponse obj = LocationResponse.mapToLocationResponse(lhm);
                result.add(obj);
            });
        }
        return result;
    }
}


