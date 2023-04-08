package com.example.videbank.mapper;
import com.example.videbank.dto.BalanceDto;
import com.example.videbank.entity.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BalanceMapper {

        @Autowired
        private AccountMapper accountMapper;

        public BalanceDto toDto(Balance balance) {
            BalanceDto balanceDto = new BalanceDto();
            balanceDto.setId(balance.getId());
            balanceDto.setAccount(accountMapper.toDto(balance.getAccount()));
            balanceDto.setCurrencyType(balance.getCurrencyType());
            balanceDto.setAmount(balance.getAmount());
            balanceDto.setDescription(balance.getDescription());
            return balanceDto;
        }

        public Balance toEntity(BalanceDto balanceDto) {
            Balance balance = new Balance();
            balance.setId(balanceDto.getId());
            balance.setAccount(accountMapper.toEntity(balanceDto.getAccount()));
            balance.setCurrencyType(balanceDto.getCurrencyType());
            balance.setAmount(balanceDto.getAmount());
            balance.setDescription(balanceDto.getDescription());
            return balance;
        }
    }



