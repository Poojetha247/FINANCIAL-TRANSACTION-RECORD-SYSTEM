package com.wipro.ftrs.util;

public class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InsufficientBalanceException: " + getMessage();
    }
}
