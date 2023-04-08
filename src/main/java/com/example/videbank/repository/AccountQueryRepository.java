package com.example.videbank.repository;
import com.example.videbank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountQueryRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findById(@Param("id") Long id);
}
