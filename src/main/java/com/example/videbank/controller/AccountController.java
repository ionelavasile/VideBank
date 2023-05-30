package com.example.videbank.controller;

import com.example.videbank.dto.AccountDto;
import com.example.videbank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getById(id);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccountDto = accountService.createAccount(accountDto);
        return ResponseEntity.ok(createdAccountDto);
    }

    @PostMapping("/save")
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto accountDto) {
        AccountDto savedAccountDto = accountService.saveAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccountDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        accountDto.setId(id);
        AccountDto updatedAccountDto = accountService.updateAccount(accountDto);
        return ResponseEntity.ok(updatedAccountDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}



