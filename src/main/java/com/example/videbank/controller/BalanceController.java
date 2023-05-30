package com.example.videbank.controller;

import com.example.videbank.dto.BalanceDto;
import com.example.videbank.exceptions.BalanceNotFoundException;
import com.example.videbank.exceptions.BalanceValidationException;
import com.example.videbank.service.BalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BalanceDto> getBalanceById(@PathVariable Long id) {
        BalanceDto balanceDto = balanceService.getBalanceById(id);
        return ResponseEntity.ok(balanceDto);
    }

    @GetMapping
    public ResponseEntity<List<BalanceDto>> getAllBalances() {
        List<BalanceDto> balanceDtos = balanceService.getAllBalances();
        return ResponseEntity.ok(balanceDtos);
    }

    @PostMapping
    public ResponseEntity<BalanceDto> createBalance(@RequestBody BalanceDto balanceDto) {
        BalanceDto createdBalance = balanceService.createBalance(balanceDto);
        return ResponseEntity
                .created(URI.create("/balances/" + createdBalance.getId()))
                .body(createdBalance);
    }
    @PostMapping("/save")
    public ResponseEntity<BalanceDto> saveBalance(@RequestBody BalanceDto balanceDto) {
        BalanceDto savedBalance = balanceService.saveBalance(balanceDto);
        return ResponseEntity
                .created(URI.create("/balances/" + savedBalance.getId()))
                .body(savedBalance);
    }


    @PutMapping("/update")
    public ResponseEntity<Void> updateBalance(@PathVariable Long id, @RequestBody BalanceDto balanceDto) {
        balanceDto.setId(id);
        balanceService.updateBalance(balanceDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBalance(@PathVariable Long id) {
        balanceService.deleteBalance(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(BalanceNotFoundException.class)
    public ResponseEntity<String> handleBalanceNotFoundException(BalanceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BalanceValidationException.class)
    public ResponseEntity<String> handleBalanceValidationException(BalanceValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}





