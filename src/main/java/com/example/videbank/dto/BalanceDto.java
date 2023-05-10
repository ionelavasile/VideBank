package com.example.videbank.dto;

import com.example.videbank.entity.Balance;
import com.example.videbank.entity.CurrencyType;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BalanceDto {
    private Long id;
    private Double balance;
    private CurrencyType currencyType;
    private Double amount;
    private AccountDto account;
    private String description;

    public static List<BalanceDto> fromEntityList(List<Balance> balances) {
        return null;
    }

    public Balance toEntity() {
        Balance balance = new Balance();
        balance.setId(this.id);
        balance.setBalance(this.balance);
        balance.setCurrencyType(this.currencyType);
        balance.setAmount(this.amount);
        balance.setAccount(this.account.toEntity());
        balance.setDescription(this.description);
        return balance;
    }
}

