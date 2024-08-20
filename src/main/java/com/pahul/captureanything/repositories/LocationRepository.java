package com.pahul.captureanything.repositories;

import com.pahul.captureanything.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends MongoRepository<Location,String> {
    List<Location> findByCityAndCountry(String city, String country);

}
