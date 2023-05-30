package com.example.videbank.dto;

import com.example.videbank.entity.Account;
import com.example.videbank.entity.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long id;
    private String accountNumber;
    private CurrencyType currencyType;
    private CustomerDto customer;
    private List<BalanceDto> balances;
    private AccountDto senderAccount;
    private AccountDto receiverAccount;

    public Double getBalance() {
        if (balances != null && !balances.isEmpty()) {
            return balances.get(0).getAmount();
        }
        return null;
    }

    public Account toEntity() {
        return Account.builder()
                .id(id)
                .accountNumber(accountNumber)
                .currencyType(currencyType)
                .customer(customer.toEntity())
                .balances(balances.stream().map(BalanceDto::toEntity).collect(Collectors.toList()))
                .build();
    }

    public static AccountDto fromEntity(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .currencyType(account.getCurrencyType())
                .customer(CustomerDto.fromEntity(account.getCustomer()))
                .balances(BalanceDto.fromEntityList(account.getBalances()))
                .build();
    }

    public AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .currencyType(account.getCurrencyType())
                .customer(CustomerDto.fromEntity(account.getCustomer()))
                .balances(BalanceDto.fromEntityList(account.getBalances()))
                .senderAccount(account.getSenderAccount() != null ? AccountDto.fromEntity(account.getSenderAccount()) : null)
                .receiverAccount(account.getReceiverAccount() != null ? AccountDto.fromEntity(account.getReceiverAccount()) : null)
                .build();
    }


}