package com.example.videbank.mapper;
import com.example.videbank.dto.AccountDto;
import com.example.videbank.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    public AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setCurrencyType(account.getCurrencyType());
        accountDto.setBalances(account.getBalances());
        return accountDto;
    }

    public static Account toEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setCurrencyType(accountDto.getCurrencyType());
        account.setBalances(accountDto.getBalances());
        return account;
    }

    public List<AccountDto> toDtoList(List<Account> accounts) {
        return accounts.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}




