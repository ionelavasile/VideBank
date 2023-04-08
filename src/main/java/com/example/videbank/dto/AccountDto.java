package com.example.videbank.dto;
import com.example.videbank.entity.Balance;
import com.example.videbank.entity.CurrencyType;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountDto {
    private Long id;
    private String accountNumber;
    private Double balance;
    private CurrencyType currencyType;
    private CustomerDto customer;
    private AccountDto senderAccount;
    private AccountDto receiverAccount;

    public AccountDto(Long id, CustomerDto customerDto, CurrencyType currencyType, List<BalanceDto> balancesDto,
                      AccountDto senderAccountDto, AccountDto receiverAccountDto) {
        this.id = id;
        this.customer = customerDto;
        this.currencyType = currencyType;
        this.senderAccount = senderAccountDto;
        this.receiverAccount = receiverAccountDto;
        if (balancesDto != null && !balancesDto.isEmpty()) {
            this.balance = balancesDto.stream().mapToDouble(BalanceDto::getAmount).sum();
        }
    }


    public void setBalances(List<Balance> balances) {
    }

    public List<Balance> getBalances() {
        return null;
    }
}
