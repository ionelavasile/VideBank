package com.example.videbank.dto;

import com.example.videbank.entity.Balance;
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
public class BalanceDto {
    private Long id;
    private Double balance;
    private CurrencyType currencyType;
    private Double amount;
    private AccountDto account;
    private String description;

    public BalanceDto(Long id, AccountDto toDto, Double amount, CurrencyType currencyType, String description) {
    }

    public static List<BalanceDto> fromEntityList(List<Balance> balances) {
        return balances.stream()
                .map(balance -> {
                    BalanceDto balanceDto = new BalanceDto();
                    balanceDto.setId(balance.getId());
                    balanceDto.setAccount(AccountDto.fromEntity(balance.getAccount()));
                    balanceDto.setCurrencyType(balance.getCurrencyType());
                    balanceDto.setAmount(balance.getAmount());
                    balanceDto.setDescription(balance.getDescription());
                    return balanceDto;
                })
                .collect(Collectors.toList());
    }



    public Balance toEntity() {
        Balance balance = new Balance();
        balance.setId(this.id);
        balance.setAmount(this.amount);
        balance.setCurrencyType(this.currencyType);
        balance.setAccount(this.account.toEntity());
        balance.setDescription(this.description);
        return balance;
    }
}



