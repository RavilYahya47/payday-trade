package com.ravilyahya.paydaytrade.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("User with this Username not found in Database!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}