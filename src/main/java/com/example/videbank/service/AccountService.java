package com.example.videbank.service;

import com.example.videbank.dto.AccountDto;
import com.example.videbank.entity.CurrencyType;
import com.example.videbank.exceptions.InsufficientBalanceException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;


public interface AccountService {

    // Retrieve an account by its ID
    AccountDto getById(Long id);

    // Retrieve a list of all accounts
    List<AccountDto> getAllAccounts();

    // Save an account (create a new account if it doesn't exist, update an existing account if it does)
    AccountDto saveAccount(AccountDto accountDto);

    // Delete an account by its ID
    void deleteAccount(Long id);

    // Create a new account
    AccountDto createAccount(AccountDto accountDto);

    // Update an existing account
    AccountDto updateAccount(AccountDto accountDto);

    // Withdraw money from an account
    void withdraw(Long accountId, Double amount) throws InsufficientBalanceException, AccountNotFoundException;

    // Deposit money into an account
    void deposit(Long accountId, Double amount) throws AccountNotFoundException;
    void transferMoney(Long senderAccountId, Long receiverAccountId, Double amount, CurrencyType currencyType) throws InsufficientBalanceException, AccountNotFoundException;

}

