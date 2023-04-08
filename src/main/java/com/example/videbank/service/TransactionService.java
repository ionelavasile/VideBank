package com.example.videbank.service;
import com.example.videbank.dto.TransactionDto;
import java.util.List;


public interface TransactionService {

    TransactionDto getById(Long id);

    List<TransactionDto> getAllTransactions();

    TransactionDto createTransaction(TransactionDto transactionDto);

    TransactionDto updateTransaction(TransactionDto transactionDto);

    void deleteTransaction(Long id);

    TransactionDto saveTransaction(TransactionDto TransactionDto);
}

