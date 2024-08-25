package com.pahul.captureanything.model;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LocationListener extends AbstractMongoEventListener<Location> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<Location> event) {
        Location location = event.getSource();
        Document document = event.getDocument();
        
        if (location!=null){
            if (location.getName() != null && location.getCountry() != null) {
                String tempLocationId = location.getName() + "-" + location.getCountry();
                location.setLocationId(tempLocationId);

                if (document != null) {
                    document.put("locationId", tempLocationId);
                }
            }else{
                String tempLocationId = UUID.randomUUID().toString().replace("-", "");
                location.setLocationId(tempLocationId);

                if (document != null) {
                    document.put("locationId", tempLocationId);
                }
            }
        }
        
        super.onBeforeSave(event);
    }
}
