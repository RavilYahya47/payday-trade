package com.ravilyahya.paydaytrade.exception;

public class OrderTargetPriceDoesntMatchException extends RuntimeException{
    public OrderTargetPriceDoesntMatchException() {
        super("Target price criteria doesn't match with stock price!\n" +
                "Your order added to the order execution pool!");
    }

    public OrderTargetPriceDoesntMatchException(String message) {
        super(message);
    }
}
