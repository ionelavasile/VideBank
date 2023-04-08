package com.example.videbank.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SenderAccountDto {
    private Long id;
    private AccountDto account;
}