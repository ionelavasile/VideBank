package com.example.videbank.repository;

import com.example.videbank.entity.Account;
import com.example.videbank.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findById(Long id);
}
