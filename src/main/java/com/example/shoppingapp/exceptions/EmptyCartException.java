package com.example.shoppingapp.exceptions;

public class EmptyCartException extends Exception{
    public EmptyCartException() {
        super("Cart is empty!");
    }
}
