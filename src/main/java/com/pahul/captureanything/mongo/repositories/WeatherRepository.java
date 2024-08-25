package com.pahul.captureanything.mongo.repositories;

import com.pahul.captureanything.model.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {

    /**
     * Finds a Weather from Mongo that has the specified locationId.
     *
     * @param  locationId the locationId to search for
     * @return          a Weather object that has the specified locationId
     */
    public Weather findByLocationId(String locationId);
    /**
     * Finds all Weather from Mongo that have the specified date and time.
     *
     * @param  dateTime the date and time to search for
     * @return          a list of Weather objects that have the specified date and time
     */
    public List<Weather> findByDateTime(LocalDateTime dateTime);
}
