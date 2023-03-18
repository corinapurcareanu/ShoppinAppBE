package com.example.shoppingapp.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotLoggedInException extends Exception{
    public UserNotLoggedInException() {
        super("User Not Logged In!");
    }
}
