package com.example.videbank.mapper;

import com.example.videbank.dto.BalanceDto;
import com.example.videbank.entity.Balance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BalanceMapper {

    private final AccountMapper accountMapper;

    public BalanceDto toDto(Balance balance) {
        return new BalanceDto(
                balance.getId(),
                accountMapper.toDto(balance.getAccount()),
                balance.getAmount(),
                balance.getCurrencyType(),
                balance.getDescription()
        );
    }

    public Balance toEntity(BalanceDto balanceDto) {
        return new Balance(
                        balanceDto.getId(),
                        accountMapper.toEntity(balanceDto.getAccount()),
                balanceDto.getCurrencyType(),
                balanceDto.getAmount(),
                        balanceDto.getDescription()
                );
    }

    public List<BalanceDto> toDtoList(List<Balance> balances) {
        return balances.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Balance> toEntityList(List<BalanceDto> balanceDto) {
        return balanceDto.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}








