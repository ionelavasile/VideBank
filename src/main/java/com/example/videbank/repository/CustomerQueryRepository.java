package com.example.videbank.repository;
import com.example.videbank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerQueryRepository extends JpaRepository<Customer,String>{
    @Query("SELECT c FROM customer c WHERE c.email = :email")
    Optional<Customer> findByEmail(@Param("email") String email);

}
