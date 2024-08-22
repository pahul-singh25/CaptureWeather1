package com.pahul.captureanything.repositories;

import com.pahul.captureanything.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAllByNameOrEmailContains(String name, String email);

    List<User> findAllByNameContains(String name);
}
