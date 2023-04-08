package com.example.videbank.service;

import com.example.videbank.dto.BalanceDto;

import java.util.List;

public interface BalanceService {
    BalanceDto getBalanceById(Long id);

    List<BalanceDto> getAllBalances();

    void updateBalance(BalanceDto balanceDto);

    BalanceDto createBalance(BalanceDto balanceDto);

    BalanceDto saveBalance(BalanceDto balanceDto);

    void deleteBalance(Long id);
}







