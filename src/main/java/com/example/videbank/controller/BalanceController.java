package com.example.videbank.controller;

import com.example.videbank.dto.BalanceDto;
import com.example.videbank.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        List<BalanceDto> balanceDto = balanceService.getAllBalances();
        return ResponseEntity.ok(balanceDto);
    }

    @PostMapping
    public ResponseEntity<BalanceDto> createBalance(@RequestBody BalanceDto balanceDto) {
        BalanceDto createdBalance = balanceService.createBalance(balanceDto);
        return ResponseEntity
                .created(URI.create("/balances/" + createdBalance.getId()))
                .body(createdBalance);
    }
    @PutMapping
    public ResponseEntity<BalanceDto> saveBalance(@RequestBody BalanceDto balanceDto) {
        BalanceDto savedBalanceDto = balanceService.saveBalance(balanceDto);
        return ResponseEntity.ok(savedBalanceDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBalance(@PathVariable Long id, @RequestBody BalanceDto balanceDto) {
        balanceDto.setId(id);
        balanceService.updateBalance(balanceDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBalance(@PathVariable Long id) {
        balanceService.deleteBalance(id);
        return ResponseEntity.noContent().build();
    }
}




