package com.pahul.captureanything.service;

import com.pahul.captureanything.model.User;

import java.util.List;


public interface UserService {

    List<User> getUsers();

    User getSelectedUser(String name, String email);

    List<User> getSelectedUser(String name);

    User addUser(User user);

    List<User>  getUserByPreferenceGreaterThan(long count);
}
