package com.example.shoppingapp.exceptions;

public class DuplicateUserException extends Exception{
    public DuplicateUserException() {
        super("Username already exists!");
    }
}
