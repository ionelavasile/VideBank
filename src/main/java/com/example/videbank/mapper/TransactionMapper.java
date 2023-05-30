package com.example.videbank.mapper;

import com.example.videbank.dto.TransactionDto;
import com.example.videbank.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    public TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .currencyType(transaction.getCurrencyType())
                .directionOfTransaction(transaction.getDirectionOfTransaction())
                .description(transaction.getDescription())
                .balanceAfterTransaction(transaction.getBalanceAfterTransaction())
                .build();
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


