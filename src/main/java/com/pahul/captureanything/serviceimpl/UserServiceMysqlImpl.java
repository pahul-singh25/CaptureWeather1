package com.pahul.captureanything.serviceimpl;

import com.pahul.captureanything.model.entity.User;
import com.pahul.captureanything.mysql.repositories.UserRepository;
import com.pahul.captureanything.service.UserMySqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceMysqlImpl implements UserMySqlService {
    @Autowired
    @Qualifier("userMySqlRepository")
    private UserRepository userRepository;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Returns a list of users that match the given name or email.
     *
     * @param name  the name to search for
     * @param email the email to search for
     * @return a list of users that match the given name or email
     */
    @Override
    public List<User> getSelectedUser(String name, String email) {
        // Find all users that match the given name or email
        return userRepository.findAllByNameOrEmailContains(name, email);
    }


    /**
     * Returns a list of users that match the given name or email.
     *
     * @param name  the name to search for
     * @return a list of users that match the given name or email
     */
    @Override
    public List<User> getSelectedUser(String name) {
        // Find all users that match the given name or email
        return userRepository.findAllByNameContains(name);
    }


    /**
     * Adds a new user to the database.
     *
     * @param user the user to add
     * @return the added user, or null if an error occurred
     */
    @Override
    public User addUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


}
