package com.example.videbank.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SenderAccountDto {
    private Long id;
    private AccountDto account;
    private Double balance;
}
