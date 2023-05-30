package com.example.videbank.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency_type")
    private CurrencyType currencyType;

    @Column(name = "direction_of_transaction")
    private DirectionOfTransaction directionOfTransaction;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private Account receiverAccount;

    @Builder
    public Transaction(Double amount, CurrencyType currencyType, DirectionOfTransaction directionOfTransaction,
                       String description, Account senderAccount, Account receiverAccount) {
        this.amount = amount;
        this.currencyType = currencyType;
        this.directionOfTransaction= directionOfTransaction;
        this.description = description;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
    }


    public Double getBalanceAfterTransaction() {
        // Implement the logic to calculate and return the balance after the transaction
        return null;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
        // Implement the logic to set the balance after the transaction
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public void setDirectionOfTransaction(DirectionOfTransaction directionOfTransaction) {
        this.directionOfTransaction = directionOfTransaction;
    }
}

