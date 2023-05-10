package com.example.videbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiverDto {
    private ReceiverAccountDto receiverAccount;
    private TransactionDto transaction;
}
