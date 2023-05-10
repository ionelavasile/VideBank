package com.example.videbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SenderDto {
    private SenderAccountDto senderAccount;
    private TransactionDto transaction;
}

