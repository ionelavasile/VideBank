package com.example.videbank.entity;

import com.example.videbank.dto.AccountDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_type", nullable = false)
    private CurrencyType currencyType;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Balance> balances = new ArrayList<>();

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
    private List<Transaction> senderTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL)
    private List<Transaction> receiverTransactions = new ArrayList<>();

    @Column(name = "account_number")
    private String accountNumber;

    public void withdraw(Double amount) {
        // Withdrawal logic
    }

    public Account getSenderAccount() {
        Optional<Transaction> optionalTransaction = senderTransactions.stream().findFirst();
        return optionalTransaction.map(Transaction::getReceiverAccount).orElse(null);
    }

    public Account getReceiverAccount() {
        Optional<Transaction> optionalTransaction = receiverTransactions.stream().findFirst();
        return optionalTransaction.map(Transaction::getSenderAccount).orElse(null);
    }

    public Balance getBalance(CurrencyType currencyType) {
        Optional<Balance> optionalBalance = balances.stream()
                .filter(balance -> balance.getCurrencyType() == currencyType)
                .findFirst();
        return optionalBalance.orElseGet(() -> {
            Balance newBalance = new Balance();
            newBalance.setAmount(0.0);
            newBalance.setCurrencyType(currencyType);
            newBalance.setAccount(this);
            balances.add(newBalance);
            return newBalance;
        });
    }


    public void deposit(Double amount, CurrencyType currencyType) {
    }

    public AccountDto toDto() {
        return null;
    }

    public Double getBalance() {
        return null;
    }
}





    



