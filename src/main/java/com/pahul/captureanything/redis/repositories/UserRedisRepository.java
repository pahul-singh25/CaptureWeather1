package com.pahul.captureanything.redis.repositories;

import com.pahul.captureanything.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisRepository extends CrudRepository<User, String> {
    User findByNameAndEmail(String name, String email);
}
