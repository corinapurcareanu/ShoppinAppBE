package com.example.shoppingapp.controller;

import com.example.shoppingapp.dto.UserDto;
import com.example.shoppingapp.exceptions.DuplicateUserException;
import com.example.shoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"/registerUser"})
    public UserDto registerNewUser(@RequestBody UserDto user) {
        try {
            return userService.registerNewUser(user);
        } catch (DuplicateUserException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
