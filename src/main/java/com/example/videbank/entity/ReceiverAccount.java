package com.example.videbank.entity;

import com.example.videbank.exceptions.InsufficientBalanceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "receiver_account")
public class ReceiverAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Double currentBalance;

    public void withdraw(Double amount) throws InsufficientBalanceException {
        if (currentBalance < amount) {
            throw new InsufficientBalanceException();
        }
        currentBalance -= amount;
        // Update the balance field in the database here using an ORM framework or a repository/service method.
    }

    public void deposit(Double amount, CurrencyType currencyType) {
        currentBalance += amount;
        // Update the balance field in the database here using an ORM framework or a repository/service method.
    }
}



