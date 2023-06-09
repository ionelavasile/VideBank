package com.example.videbank.dto;
import com.example.videbank.entity.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private Long id;
    private Double amount;
    private CurrencyType currencyType;
    private DirectionOfTransaction directionOfTransaction;
    private String description;
    private Double balanceAfterTransaction;
    private AccountDto senderAccount;
    private AccountDto receiverAccount;
}

