package com.ravilyahya.paydaytrade.exception;

public class UserFoundException extends Exception{
    public UserFoundException() {
        super("User with this Username is already exists! Try to get another username!");
    }

    public UserFoundException(String message) {
        super(message);
    }
}