package com.pahul.captureanything.mysql.repositories;

import com.pahul.captureanything.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userMySqlRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByNameOrEmailContains(String name, String email);

    List<User> findAllByNameContains(String name);
}
