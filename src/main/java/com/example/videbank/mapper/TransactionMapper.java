package com.example.videbank.mapper;

import com.example.videbank.dto.TransactionDto;
import com.example.videbank.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    public TransactionDto toDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setCurrencyType(transaction.getCurrencyType());
        transactionDto.setDirectionOfTransaction(transaction.getDirectionOfTransaction());
        transactionDto.setDescription(transaction.getDescription());
        transactionDto.setBalanceAfterTransaction(transaction.getBalanceAfterTransaction());
        return transactionDto;
    }

    public Transaction toEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCurrencyType(transactionDto.getCurrencyType());
        transaction.setDirectionOfTransaction(transactionDto.getDirectionOfTransaction());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setBalanceAfterTransaction(transactionDto.getBalanceAfterTransaction());
        return transaction;
    }

    public List<TransactionDto> toDtoList(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

