package com.example.shoppingapp.controller;

import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"/registerUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }
}
