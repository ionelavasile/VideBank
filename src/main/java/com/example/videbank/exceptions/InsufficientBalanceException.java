package com.example.videbank.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Insufficient balance");
    }

    public InsufficientBalanceException(Long id, Double amount, Double amount1) {
        super("Insufficient balance");
    }
}


