package com.pahul.captureanything.service;

import com.pahul.captureanything.model.entity.User;

import java.util.List;


public interface UserMySqlService {

    List<User> getUsers();

    List<User> getSelectedUser(String name, String email);

    List<User> getSelectedUser(String name);

    User addUser(User user);

}
