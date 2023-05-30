package com.example.videbank.repository;
import com.example.videbank.entity.CurrencyType;
import com.example.videbank.entity.DirectionOfTransaction;
import com.example.videbank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TransactionQueryRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.directionOfTransaction = :direction AND t.currencyType = :currency")
    List<Transaction> findByDirectionOfTransactionAnAndCurrencyType(@Param("direction") DirectionOfTransaction direction,
                                                                  @Param("currency") CurrencyType currencyType);
}





