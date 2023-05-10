package com.example.videbank.entity;

import lombok.*;

import javax.persistence.*;

@Entity
    @Data
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "balance")
    public class Balance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id", nullable = false)
        private Account account;

        @Enumerated(EnumType.STRING)
        @Column(name = "currency_type", nullable = false)
        private CurrencyType currencyType;

        @Column(name = "amount", nullable = false)
        private Double amount;

        @Column(name = "description")
        private String description;


        public void setBalance(Double balance) {
        }
    }


