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
    private String address;
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


    public Location(String address, String locationId,
                        String name, String region, String country, double lat,
                            double lon, String tz_id,
                                    long localtime_epoch, String localtime) {
        this.address = address;
        this.locationId = createLocationId();
        this.name = name;
        this.region = region;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.tz_id = tz_id;
        this.localtime_epoch = localtime_epoch;
        this.localtime = localtime;
    }

    /**
     to call this method after setting the either fields of the location
     * If either name or country is null, generates a random UUID and assigns it to the locationId.
     */
    public void setLocationId() {
        this.locationId = createLocationId();
    }

    public String createLocationId() {
        StringBuilder locationId = new StringBuilder();
        if (this.getName() != null) {
            locationId.append(this.getName());
        }
        if (this.getRegion() != null) {
            if (!locationId.isEmpty()) {
                locationId.append("-");
            }
            locationId.append(this.getRegion());
        }
        if (this.getCountry() != null) {
            if (!locationId.isEmpty()) {
                locationId.append("-");
            }
            locationId.append(this.getCountry());
        }
        if (locationId.isEmpty()) {
            locationId.append("-");
        }
        String uud = UUID.randomUUID().toString().replace("-", "");
        return  locationId.append(uud).toString();
    }

    public String getLocationId(){
        if(this.locationId==null){
            setLocationId(createLocationId());
        }
        return this.locationId;
    }

    public String getAddress(){
        if(this.address==null){
            setAddress(createAddress());
        }
        return this.address;
    }

    public String createAddress() {
        StringBuilder address = new StringBuilder();
        if (this.getName() != null) {
            address.append(this.getName());
        }
        if (this.getRegion() != null) {
            if (!address.isEmpty()) {
                address.append(" ");
            }
            address.append(this.getRegion());
        }
        if (this.getCountry() != null) {
            if (!address.isEmpty()) {
                address.append(",");
            }
            address.append(this.getCountry());
        }
        if (address.isEmpty()) {
            address.append("unknown");
        }
        return address.toString();
    }
}