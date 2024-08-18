package com.pahul.captureanything.service;

import com.pahul.captureanything.model.User;

import java.util.List;


public interface UserService {

    List<User> getUsers();

    List<User> getSelectedUser(String name, String email);

    User addUser(User user);
}
