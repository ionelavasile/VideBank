package com.example.videbank.dto;
import com.example.videbank.entity.CurrencyType;
import lombok.*;

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

}
