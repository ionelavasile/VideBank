package com.example.videbank.service.impl;

import com.example.videbank.dto.TransactionDto;
import com.example.videbank.entity.CurrencyType;
import com.example.videbank.entity.DirectionOfTransaction;
import com.example.videbank.entity.Transaction;
import com.example.videbank.mapper.TransactionMapper;
import com.example.videbank.repository.TransactionRepository;
import com.example.videbank.service.TransactionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDto getById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + id));
        return transactionMapper.toDto(transaction);
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactionMapper.toDtoList(transactions);
    }

    @Override
    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + id));
        transactionRepository.delete(transaction);
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }

    @Override
    public TransactionDto updateTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionRepository.findById(transactionDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + transactionDto.getId()));

        // Update the transaction entity with the values from the DTO
        transaction.setDirectionOfTransaction(transactionDto.getDirectionOfTransaction());
        transaction.setCurrencyType(transactionDto.getCurrencyType());

        transaction = transactionRepository.save(transaction);

        return transactionMapper.toDto(transaction);
    }

    @Override
    public List<TransactionDto> findByDirectionOfTransactionAndCurrencyType(DirectionOfTransaction direction, CurrencyType currencyType) {
        List<Transaction> transactions = transactionRepository.findByDirectionOfTransactionAndCurrencyType(direction, currencyType);
        return transactionMapper.toDtoList(transactions);
    }
}




