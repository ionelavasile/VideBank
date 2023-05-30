package com.example.videbank.exceptions;

public class BalanceValidationException extends RuntimeException {
    public BalanceValidationException(String message) {
        super(message);
    }
}
