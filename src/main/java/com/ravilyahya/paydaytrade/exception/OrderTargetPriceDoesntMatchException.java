package com.ravilyahya.paydaytrade.exception;

public class OrderTargetPriceDoesntMatchException extends RuntimeException{
    public OrderTargetPriceDoesntMatchException() {
        super("Target price criteria doesn't match with stock price!");
    }

    public OrderTargetPriceDoesntMatchException(String message) {
        super(message);
    }
}
