package com.example.videbank.controller;

import com.example.videbank.dto.TransactionDto;
import com.example.videbank.mapper.TransactionMapper;
import com.example.videbank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        TransactionDto transactionDto = transactionService.getById(id);
        return ResponseEntity.ok(transactionDto);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactionDto = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionDto);
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto createdTransactionDto = transactionService.createTransaction(transactionDto);
        return ResponseEntity.created(URI.create("/api/transactions/" + createdTransactionDto.getId()))
                .body(createdTransactionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        transactionDto.setId(id);
        TransactionDto updatedTransactionDto = transactionService.updateTransaction(transactionDto);
        return ResponseEntity.ok(updatedTransactionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
