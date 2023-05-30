package com.example.videbank.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Insufficient funds in the account");
    }

    public InsufficientBalanceException(Long id, Double currentBalance, Double attemptedWithdrawal) {
        super("Account ID " + id + " does not have sufficient funds. Current balance: "
                + currentBalance + ", attempted withdrawal: " + attemptedWithdrawal);
    }
}


