package com.pahul.captureanything.repositories;

import com.pahul.captureanything.model.OldLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends MongoRepository<OldLocation,String> {
    List<OldLocation> findByCityAndCountry(String city, String country);

}
