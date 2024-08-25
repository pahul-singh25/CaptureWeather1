package com.pahul.captureanything.controller;

import com.pahul.captureanything.model.User;
import com.pahul.captureanything.serviceimpl.UserServiceImpl;
import com.pahul.captureanything.serviceimpl.UserServiceMysqlImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserServiceImpl userService;

    private final UserServiceMysqlImpl userMySqlService;

    @Autowired
    public UserController(UserServiceImpl userService, UserServiceMysqlImpl userMySqlService) {
        this.userService = userService;
        this.userMySqlService = userMySqlService;
    }

    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET, produces ="application/json")
    public List<User> getAllUsers(){
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            return userService.getUsers();
        }finally {
            watch.stop();
            LOGGER.info("Time taken By /getAllUsers : {} ", watch.getTotalTimeMillis() + " ms");
        }
    }

/*
        @RequestMapping(value = "/getWeather",method = RequestMethod.GET, produces ="application/json")
        public List<User> getWeather(@RequestParam("name") String name, @RequestParam("email") String email ){
            StopWatch watch = new StopWatch();
            watch.start();
            try {
                return userService.getWeather(name, email);
            }finally {
                watch.stop();
                LOGGER.info("Time taken By /getAllUsers : " + watch.getTotalTimeMillis() + " ms");
            }
        }
 */

    @RequestMapping(value = "/getSelectedUsers",method = RequestMethod.GET, produces ="application/json")
    public User getSelectedUsers(@RequestParam("name") String name, @RequestParam("email") String email )
    {
           StopWatch watch = new StopWatch();
            watch.start();
            try {
                return userService.getSelectedUser(name, email);
            }finally {
                watch.stop();
                LOGGER.info("Time taken By /getSelectedUsers : " + watch.getTotalTimeMillis() + " ms");
            }

    }



    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }



    @RequestMapping(value = "/getAllMySqlUsers",method = RequestMethod.GET, produces ="application/json")
    public List<com.pahul.captureanything.model.entity.User> getAllMySqlUsers(){
        return userMySqlService.getUsers();
    }



}
