package com.example.videbank.service;

import com.example.videbank.dto.BalanceDto;
import com.example.videbank.exceptions.BalanceNotFoundException;
import com.example.videbank.exceptions.BalanceValidationException;

import java.util.List;

public interface BalanceService {
    BalanceDto getBalanceById(Long id);

    List<BalanceDto> getAllBalances();

    void updateBalance(BalanceDto balanceDto) throws BalanceNotFoundException, BalanceValidationException;

    BalanceDto createBalance(BalanceDto balanceDto) throws BalanceValidationException;

    BalanceDto saveBalance(BalanceDto balanceDto) throws BalanceValidationException;

    void deleteBalance(Long id) throws BalanceNotFoundException;
}







