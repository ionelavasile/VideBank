package com.example.videbank.mapper;
import com.example.videbank.dto.AccountDto;
import com.example.videbank.dto.BalanceDto;
import com.example.videbank.dto.CustomerDto;
import com.example.videbank.entity.Account;
import com.example.videbank.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    @Autowired
    private CustomerMapper customerMapper;

    public AccountDto toDto(Account account, BalanceMapper balanceMapper) { // add BalanceMapper argument here
        List<BalanceDto> balanceDtos = balanceMapper.toDtoList(account.getBalances()); // pass balanceMapper here
        return AccountDto.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .currencyType(account.getCurrencyType())
                .customer(customerMapper.toDto(account.getCustomer()))
                .balances(balanceDtos)
                .senderAccount(account.getSenderAccount() != null ?
                        this.toDto(account.getSenderAccount(), balanceMapper) : null) // pass balanceMapper here
                .receiverAccount(account.getReceiverAccount() != null ?
                        this.toDto(account.getReceiverAccount(), balanceMapper) : null) // pass balanceMapper here
                .build();
    }

    public Account toEntity(AccountDto accountDto) {
        return accountDto.toEntity();
    }

    public List<AccountDto> toDtoList(List<Account> accounts, BalanceMapper balanceMapper) { // add BalanceMapper argument here
        return accounts.stream()
                .map(account -> toDto(account, balanceMapper)) // pass balanceMapper here
                .collect(Collectors.toList());
    }

    public List<Account> toEntityList(List<AccountDto> accountDtos) {
        return accountDtos.stream()
                .map(AccountDto::toEntity)
                .collect(Collectors.toList());
    }

    public static AccountDto fromEntity(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .currencyType(account.getCurrencyType())
                .customer(CustomerDto.fromEntity(account.getCustomer()))
                .balances(BalanceDto.fromEntityList(account.getBalances()))
                .senderAccount(account.getSenderAccount() != null ?
                        AccountDto.builder().id(account.getSenderAccount().getId()).build() : null)
                .receiverAccount(account.getReceiverAccount() != null ?
                        AccountDto.builder().id(account.getReceiverAccount().getId()).build() : null)
                .build();
    }

    public AccountMapper getCustomerMapper() {
        return null;
    }

    public List<AccountDto> toDtoList(List<Account> accounts) {
        return null;
    }

    public AccountDto toDto(Account savedAccount) {
        return null;
    }
}









