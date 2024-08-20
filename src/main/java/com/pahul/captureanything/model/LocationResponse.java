package com.pahul.captureanything.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.LinkedHashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LocationResponse {

    private long place_id;
    private String osm_type;
    private long osm_id;
    private String lat;
    private String lon;
    private String display_name;
    private String addresstype;
    private String name;

    public static LocationResponse mapToLocationResponse(LinkedHashMap lmap){
        LocationResponse response = new LocationResponse();
        if(lmap.containsKey("lat")) {
            response.setLat((String) lmap.get("lat"));
        }
        if(lmap.containsKey("lon")) {
            response.setLon((String) lmap.get("lon"));
        }
        if(lmap.containsKey("display_name")) {
            response.setDisplay_name((String) lmap.get("display_name"));
        }
        if(lmap.containsKey("addresstype")) {
            response.setAddresstype((String) lmap.get("addresstype"));
        }
        if(lmap.containsKey("name")) {
            response.setName((String) lmap.get("name"));
        }

        return response;
    }

}
