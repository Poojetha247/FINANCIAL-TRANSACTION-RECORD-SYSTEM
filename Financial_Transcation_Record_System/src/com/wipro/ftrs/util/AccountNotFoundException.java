package com.wipro.ftrs.util;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "AccountNotFoundException: " + getMessage();
    }
}
