package com.example.videbank.repository;

import com.example.videbank.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BalanceQueryRepository extends JpaRepository<Balance, Long> {
    @Query("SELECT b FROM Balance b WHERE b.id = :id")
    Optional<Balance> findById(@Param("id") Long id);
}
