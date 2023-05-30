package com.example.videbank.mapper;

import com.example.videbank.dto.AccountDto;
import com.example.videbank.dto.BalanceDto;
import com.example.videbank.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Lazy
public class AccountMapper {

    private CustomerMapper customerMapper;
    private BalanceMapper balanceMapper;

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Autowired
    public void setBalanceMapper(BalanceMapper balanceMapper) {
        this.balanceMapper = balanceMapper;
    }
    public AccountDto toDto(Account account) {
        List<BalanceDto> balanceDtos = balanceMapper.toDtoList(account.getBalances());
        return AccountDto.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .currencyType(account.getCurrencyType())
                .customer(customerMapper.toDto(account.getCustomer()))
                .balances(balanceDtos)
                .senderAccount(account.getSenderAccount() != null ? this.toDto(account.getSenderAccount()) : null)
                .receiverAccount(account.getReceiverAccount() != null ? this.toDto(account.getReceiverAccount()) : null)
                .build();
    }

    public Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.getId())
                .accountNumber(accountDto.getAccountNumber())
                .currencyType(accountDto.getCurrencyType())
                .customer(customerMapper.toEntity(accountDto.getCustomer()))
                .balances(balanceMapper.toEntityList(accountDto.getBalances()))
                .build();
    }

    public List<AccountDto> toDtoList(List<Account> accounts) {
        return accounts.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Account> toEntityList(List<AccountDto> accountDtos) {
        return accountDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}





