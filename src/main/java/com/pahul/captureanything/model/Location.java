package com.pahul.captureanything.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@JsonSerialize
@JsonRootName(value="")
@Document("Location")
public class Location implements Serializable {
    @Id
    private String id;

    @Indexed(unique = true)
    private String locationId;

    private String name;//city
    private String region;//state
    private String country;
    private double lat;
    private double lon;
    private String tz_id;
    private long localtime_epoch;
    private String localtime;



    /**
to call this method after setting the ither fields of the location
     * If either name or country is null, generates a random UUID and assigns it to the locationId.
     */
    public void setLocationId() {
        this.locationId = createLocationId();
    }

    public String createLocationId() {
        if (this.getName() != null && this.getCountry() != null) {
            return this.getName() + "-" + this.getCountry();
        }else{
            return  UUID.randomUUID().toString().replace("-", "");
        }
    }
}