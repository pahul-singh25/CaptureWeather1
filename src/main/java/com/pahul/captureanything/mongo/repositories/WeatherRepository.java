package com.pahul.captureanything.mongo.repositories;

import com.pahul.captureanything.model.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends MongoRepository<WeatherData, String> {


}
