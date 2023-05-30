package com.example.videbank.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequestDto {
    private String username;
    private String password;

}