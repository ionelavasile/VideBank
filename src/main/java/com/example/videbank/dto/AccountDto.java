package com.example.videbank.dto;

import com.example.videbank.entity.Account;
import com.example.videbank.entity.CurrencyType;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountDto {
    private Long id;
    private String accountNumber;
    private Double balance;
    private CurrencyType currencyType;
    private CustomerDto customer;
    private List<BalanceDto> balances;
    private AccountDto senderAccount;
    private AccountDto receiverAccount;

    public Account toEntity() {
        return Account.builder()
                .id(id)
                .currencyType(currencyType)
                .customer(customer.toEntity())
                .balances(balances.stream().map(BalanceDto::toEntity).collect(Collectors.toList()))
                .build();
    }

    public CustomerDto getCustomer() {
        return customer;
    }


    public AccountDto getSenderAccount() {
        return senderAccount;
    }

    public AccountDto getReceiverAccount() {
        return receiverAccount;
    }
}




