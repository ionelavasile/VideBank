package com.example.videbank.repository;

import com.example.videbank.entity.Account;
import com.example.videbank.entity.CurrencyType;
import com.example.videbank.entity.DirectionOfTransaction;
import com.example.videbank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDirectionOfTransactionAndCurrencyType(DirectionOfTransaction direction, CurrencyType currencyType);
}

