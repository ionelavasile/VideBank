package com.example.videbank.service.impl;

import com.example.videbank.dto.BalanceDto;
import com.example.videbank.entity.Balance;
import com.example.videbank.mapper.BalanceMapper;
import com.example.videbank.repository.BalanceRepository;
import com.example.videbank.service.BalanceService;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;

    public BalanceServiceImpl(BalanceRepository balanceRepository, BalanceMapper balanceMapper) {
        this.balanceRepository = balanceRepository;
        this.balanceMapper = balanceMapper;
    }

    @Override
    public BalanceDto getBalanceById(Long id) {
        Balance balance = balanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Balance not found with id: " + id));
        return balanceMapper.toDto(balance);
    }

    @Override
    public List<BalanceDto> getAllBalances() {
        List<Balance> balances = balanceRepository.findAll();
        return balances.stream().map(balanceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void updateBalance(BalanceDto balanceDto) {
        Balance balance = balanceRepository.findById(balanceDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Balance not found with id: " + balanceDto.getId()));
        balanceMapper.toEntity(balanceDto);
        balanceRepository.save(balance);
    }

    @Override
    public BalanceDto createBalance(BalanceDto balanceDto) {
        Balance balance = balanceMapper.toEntity(balanceDto);
        balance = balanceRepository.save(balance);
        return balanceMapper.toDto(balance);
    }

    @Override
    public void deleteBalance(Long id) {
        Balance balance = balanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Balance not found with id: " + id));
        balanceRepository.delete(balance);
    }

    @Override
    public BalanceDto saveBalance(BalanceDto balanceDto) {
        Balance balance = balanceMapper.toEntity(balanceDto);
        balance = balanceRepository.save(balance);
        return balanceMapper.toDto(balance);
    }
}



