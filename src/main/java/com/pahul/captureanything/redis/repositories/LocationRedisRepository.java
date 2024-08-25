package com.pahul.captureanything.redis.repositories;

import com.pahul.captureanything.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRedisRepository extends CrudRepository<Location, String> {
    Location findByNameAndRegion(String name, String country);
}
