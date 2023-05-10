package com.example.videbank.service;

import com.example.videbank.dto.AccountDto;
import com.example.videbank.dto.TransactionDto;
import com.example.videbank.entity.CurrencyType;
import com.example.videbank.exceptions.ResourcesNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransferMoney {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransferMoney(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Transactional
    public TransactionDto transferMoney(Long senderAccountId, Long receiverAccountId, Double amount, CurrencyType currencyType, String description) {
        // Retrieve sender and receiver accounts
        AccountDto senderAccountDto = accountService.getById(senderAccountId);
        AccountDto receiverAccountDto = accountService.getById(receiverAccountId);

        // check if accounts are not null and sender account has enough balance
        if (senderAccountDto == null || receiverAccountDto == null) {
            throw new ResourcesNotFoundException("Sender or receiver account not found");
        }
        if (senderAccountDto.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        // perform the transfer
        senderAccountDto.setBalance(senderAccountDto.getBalance() - amount);
        receiverAccountDto.setBalance(receiverAccountDto.getBalance() + amount);
        accountService.saveAccount(senderAccountDto);
        accountService.saveAccount(receiverAccountDto);

        // create transaction record
        TransactionDto transactionDto = TransactionDto.builder()
                .senderAccount(senderAccountDto)
                .receiverAccount(receiverAccountDto)
                .amount(amount)
                .currencyType(currencyType)
                .description(description)
                .build();
        transactionService.createTransaction(transactionDto);

        return transactionDto;
    }
}





