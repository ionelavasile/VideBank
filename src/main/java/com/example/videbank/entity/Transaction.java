package com.example.videbank.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public Double getBalanceAfterTransaction() {
        return null;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
    }
}

