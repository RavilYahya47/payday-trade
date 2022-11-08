package com.ravilyahya.paydaytrade.exception;

public class BalanceIsNotEnoughException extends RuntimeException{
    public BalanceIsNotEnoughException() {
        super("You don't have enough balance to place this operation");
    }

    public BalanceIsNotEnoughException(String message) {
        super(message);
    }
}
