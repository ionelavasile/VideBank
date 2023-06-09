package com.example.videbank.service.impl;

import com.example.videbank.dto.AccountDto;
import com.example.videbank.dto.BalanceDto;
import com.example.videbank.dto.CustomerDto;
import com.example.videbank.entity.Account;
import com.example.videbank.entity.CurrencyType;
import com.example.videbank.entity.DirectionOfTransaction;
import com.example.videbank.entity.Transaction;
import com.example.videbank.exceptions.InsufficientBalanceException;
import com.example.videbank.exceptions.ResourcesNotFoundException;
import com.example.videbank.mapper.AccountMapper;
import com.example.videbank.mapper.BalanceMapper;
import com.example.videbank.mapper.CustomerMapper;
import com.example.videbank.repository.AccountRepository;
import com.example.videbank.repository.TransactionRepository;
import com.example.videbank.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionRepository transactionRepository;
    private final CustomerMapper customerMapper;
    private final BalanceMapper balanceMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, TransactionRepository transactionRepository, CustomerMapper customerMapper, BalanceMapper balanceMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.transactionRepository = transactionRepository;
        this.customerMapper = customerMapper;
        this.balanceMapper = balanceMapper;
    }
    @Override
    public AccountDto getById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Account not found with id " + id));
        CustomerDto customerDto = customerMapper.toDto(account.getCustomer());
        List<BalanceDto> balanceDtos = account.getBalances().stream()
                .map(balance -> balanceMapper.toDto(balance))
                .collect(Collectors.toList());

        AccountDto senderAccountDto = null;
        AccountDto receiverAccountDto = null;

        if (account.getSenderAccount() != null) {
            senderAccountDto = accountMapper.toDto(account.getSenderAccount());
        }

        if (account.getReceiverAccount() != null) {
            receiverAccountDto = accountMapper.toDto(account.getReceiverAccount());
        }

        return AccountDto.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .currencyType(account.getCurrencyType())
                .customer(customerDto)
                .balances(balanceDtos)
                .senderAccount(senderAccountDto)
                .receiverAccount(receiverAccountDto)
                .build();
    }


    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.toDtoList(accounts);
    }

    @Override
    public AccountDto saveAccount(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toDto(savedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Account not found with id " + id));
        accountRepository.delete(account);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        account = accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        Account account = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountDto.getId()));
        accountMapper.toEntity(accountDto);
        account = accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    public void transferMoney(Long senderAccountId, Long receiverAccountId, Double amount, CurrencyType currencyType) throws InsufficientBalanceException, AccountNotFoundException {
        Account senderAccount = accountRepository.findById(senderAccountId).orElseThrow(() -> new AccountNotFoundException());
        Account receiverAccount = accountRepository.findById(receiverAccountId).orElseThrow(() -> new AccountNotFoundException());
        Double senderAccountBalance = senderAccount.getBalance(currencyType).getAmount();
        if (senderAccountBalance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException(senderAccount.getId(), senderAccountBalance, amount);
        }
        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount, currencyType);
        Transaction transaction = Transaction.builder()
                .amount(amount)
                .currencyType(currencyType)
                .directionOfTransaction(DirectionOfTransaction.OUT)
                .senderAccount(senderAccount)
                .receiverAccount(receiverAccount)
                .build();
        transactionRepository.save(transaction);
    }


    @Override
    public void withdraw(Long senderAccountId, Double amount) throws InsufficientBalanceException, AccountNotFoundException {
        Account senderAccount = accountRepository.findById(senderAccountId)
                .orElseThrow(() -> new AccountNotFoundException());

        Double balance = senderAccount.getBalance();
        if (balance < amount) {
            throw new InsufficientBalanceException(senderAccount.getId(), balance, amount);
        }

        senderAccount.withdraw(amount);
    }

    @Override
    public void deposit(Long receiverAccountId, Double amount) throws AccountNotFoundException {
        Account account = accountRepository.findById(receiverAccountId)
                .orElseThrow(() -> new AccountNotFoundException());
        account.deposit(amount, account.getCurrencyType());
    }
}
