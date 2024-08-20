package com.pahul.captureanything.serviceimpl;

import com.pahul.captureanything.model.User;
import com.pahul.captureanything.repositories.UserRepository;
import com.pahul.captureanything.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository  userRepository;;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getSelectedUser(String name, String email) {
        return userRepository.findAllByNameOrEmailContains(name,email);
    }

    @Override
    public User addUser(User user) {
        try {
            return userRepository.insert(user);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
