package com.example.videbank.entity;

import com.example.videbank.dto.CustomerDto;
import com.example.videbank.mapper.CustomerMapper;
import lombok.*;

import javax.persistence.*;
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
    private List<Balance> balances;

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
    private List<Transaction> senderTransactions;

    @OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL)
    private List<Transaction> receiverTransactions;

    public String getAccountNumber() {
        return null;
    }

    public void setAccountNumber(String accountNumber) {
    }

    public void withdraw(Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to withdraw must be greater than zero");
        }
        Balance balance = getBalanceForWithdrawal();
        if (balance.getAmount() < amount) {
            throw new IllegalStateException("Insufficient balance");
        }
        balance.setAmount(balance.getAmount() - amount);
        addTransaction(amount, CurrencyType.USD, DirectionOfTransaction.OUT, "Withdraw");
    }

    private Balance getBalanceForWithdrawal() {
        Optional<Balance> optionalBalance = balances.stream()
                .filter(balance -> balance.getCurrencyType() == CurrencyType.USD)
                .findFirst();
        return optionalBalance.orElseThrow(() -> new IllegalStateException("No balance found"));
    }

    public void deposit(Double amount, CurrencyType currencyType) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to deposit must be greater than zero");
        }
        Balance balance = getBalance(currencyType);
        balance.setAmount(balance.getAmount() + amount);
        addTransaction(amount, CurrencyType.USD, DirectionOfTransaction.IN, "Deposit");
    }

    public Balance getBalance(CurrencyType currencyType) {
        Optional<Balance> optionalBalance = balances.stream()
                .filter(balance -> balance.getCurrencyType() == CurrencyType.USD)
                .findFirst();
        return optionalBalance.orElseGet(() -> {
            Balance newBalance = new Balance();
            newBalance.setAmount(0.0);
            newBalance.setCurrencyType(CurrencyType.USD);
            newBalance.setAccount(this);
            balances.add(newBalance);
            return newBalance;
        });
    }

    private void addTransaction(Double amount, CurrencyType currencyType, DirectionOfTransaction direction, String description) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCurrencyType(currencyType);
        transaction.setDirectionOfTransaction(direction);
        transaction.setDescription(description);
        if (direction == DirectionOfTransaction.OUT) {
            transaction.setSenderAccount(this);
        } else {
            transaction.setReceiverAccount(this);
        }
        senderTransactions.add(transaction);
        receiverTransactions.add(transaction);
    }


    public void setBalance(Double balance) {
    }
    public void setCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            this.customer = null;
        } else {
            this.customer = CustomerMapper.toEntity(customerDto).toEntity();
        }
    }



    public void setSenderAccount(Account senderAccount) {
    }

    public void setReceiverAccount(Account receiverAccount) {
    }

    public Account getSenderAccount() {
        Optional<Transaction> optionalTransaction = senderTransactions.stream().findFirst();
        return optionalTransaction.map(Transaction::getReceiverAccount).orElse(null);
    }

    public Account getReceiverAccount() {
        Optional<Transaction> optionalTransaction = receiverTransactions.stream().findFirst();
        return optionalTransaction.map(Transaction::getSenderAccount).orElse(null);
    }

    public Double getBalance() {
        return null;
    }
}





    



