package com.example.videbank.service.impl;
import com.example.videbank.dto.AccountDto;
import com.example.videbank.entity.Account;
import com.example.videbank.mapper.AccountMapper;
import com.example.videbank.repository.AccountRepository;
import com.example.videbank.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDto getById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
        return accountMapper.toDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.toDtoList(accounts);
    }

    @Override
    public AccountDto saveAccount(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        account = accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
        accountRepository.delete(account);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        account = accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        Account account = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountDto.getId()));
        accountMapper.toEntity(accountDto);
        account = accountRepository.save(account);
        return accountMapper.toDto(account);
    }
}

