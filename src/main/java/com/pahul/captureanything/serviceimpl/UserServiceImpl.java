package com.pahul.captureanything.serviceimpl;

import com.pahul.captureanything.model.User;
import com.pahul.captureanything.mongo.repositories.UserRepository;
import com.pahul.captureanything.redis.repositories.UserRedisRepository;
import com.pahul.captureanything.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository  userRepository;

    private final  UserRedisRepository userRedisRepository;
@Autowired
    public UserServiceImpl(UserRepository userRepository, UserRedisRepository userRedisRepository) {
        this.userRepository = userRepository;
        this.userRedisRepository = userRedisRepository;
    }

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
    public User getSelectedUser(String name, String email) {
        User u = userRedisRepository.findByNameAndEmail(name,email);
        if(u!=null){
            return u;
        }else {
            u = userRepository.findAllByNameAndEmail(name,email);
            userRedisRepository.save(u);
            return u;
        }
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
             User _user = userRepository.insert(user);
            return userRedisRepository.save(_user);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<User> getUserByPreferenceGreaterThan(long count) {
        return List.of();
    }


}
