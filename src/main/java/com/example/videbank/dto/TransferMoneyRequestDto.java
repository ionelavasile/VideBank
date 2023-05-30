package com.example.videbank.dto;

import com.example.videbank.entity.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferMoneyRequestDto {
    private Long senderAccountId;
    private Long receiverAccountId;
    private Double amount;
    private CurrencyType currencyType;
    private String description;
}

