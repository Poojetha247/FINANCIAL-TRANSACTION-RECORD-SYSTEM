package com.wipro.ftrs.util;

public class InvalidTransactionException extends Exception {

    public InvalidTransactionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidTransactionException: " + getMessage();
    }
}
