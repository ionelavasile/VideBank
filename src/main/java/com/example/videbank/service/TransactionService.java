package com.example.videbank.service;

import com.example.videbank.dto.TransactionDto;
import com.example.videbank.entity.CurrencyType;
import com.example.videbank.entity.DirectionOfTransaction;

import java.util.List;


public interface TransactionService {

    TransactionDto getById(Long id);

    List<TransactionDto> getAllTransactions();

    TransactionDto createTransaction(TransactionDto transactionDto);

    TransactionDto updateTransaction(TransactionDto transactionDto);

    void deleteTransaction(Long id);

    TransactionDto saveTransaction(TransactionDto transactionDto);

    List<TransactionDto> findByDirectionOfTransactionAndCurrencyType(DirectionOfTransaction direction, CurrencyType currencyType);
}




