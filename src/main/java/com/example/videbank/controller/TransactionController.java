package com.example.videbank.controller;

import com.example.videbank.dto.TransactionDto;
import com.example.videbank.dto.TransferMoneyRequestDto;
import com.example.videbank.entity.CurrencyType;
import com.example.videbank.mapper.TransactionMapper;
import com.example.videbank.service.TransactionService;
import com.example.videbank.service.TransferMoney;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
    private final TransferMoney transferMoney;


    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper, TransferMoney transferMoney) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
        this.transferMoney = transferMoney;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        TransactionDto transactionDto = transactionService.getById(id);
        return ResponseEntity.ok(transactionDto);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactionDtos = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionDtos);
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto createdTransactionDto = transactionService.createTransaction(transactionDto);
        return ResponseEntity.created(URI.create("/api/transactions/" + createdTransactionDto.getId()))
                .body(createdTransactionDto);
    }

    @PostMapping("/save")
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto savedTransactionDto = transactionService.saveTransaction(transactionDto);
        return ResponseEntity.created(URI.create("/api/transactions/" + savedTransactionDto.getId()))
                .body(savedTransactionDto);
    }

    @PutMapping("/update")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        transactionDto.setId(id);
        TransactionDto updatedTransactionDto = transactionService.updateTransaction(transactionDto);
        return ResponseEntity.ok(updatedTransactionDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDto> transferMoney(@RequestBody TransferMoneyRequestDto transferRequestDto) {

        Long senderAccountId = transferRequestDto.getSenderAccountId();
        Long receiverAccountId = transferRequestDto.getReceiverAccountId();
        Double amount = transferRequestDto.getAmount();
        CurrencyType currencyType = transferRequestDto.getCurrencyType();
        String description = transferRequestDto.getDescription();

        TransactionDto transactionDto = transferMoney.transferMoney(senderAccountId, receiverAccountId, amount, currencyType, description);
        return ResponseEntity.ok(transactionDto);
    }
}

