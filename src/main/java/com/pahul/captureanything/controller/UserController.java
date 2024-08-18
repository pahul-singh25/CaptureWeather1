package com.pahul.captureanything.controller;

import com.pahul.captureanything.model.User;
import com.pahul.captureanything.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET, produces ="application/json")
    public List<User> getAllUsers(){
      return userService.getUsers();
    }

    @RequestMapping(value = "/getSelectedUsers",method = RequestMethod.GET, produces ="application/json")
    public List<User> getSelectedUsers(@RequestParam("name") String name,@RequestParam("email") String email )
    {
        return userService.getSelectedUser(name,email);
    }

    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user)
    {

        return userService.addUser(user);
    }




}
