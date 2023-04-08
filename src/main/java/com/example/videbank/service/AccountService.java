package com.example.videbank.service;

import com.example.videbank.dto.AccountDto;
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
}