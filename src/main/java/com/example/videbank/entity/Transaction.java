package com.example.videbank.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
        this.directionOfTransaction = directionOfTransaction;
        this.description = description;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
    }

    public Double getBalanceAfterTransaction() {
        return null;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
    }
}


