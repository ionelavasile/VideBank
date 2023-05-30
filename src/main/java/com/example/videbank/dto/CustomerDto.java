package com.example.videbank.dto;

import com.example.videbank.entity.Account;
import com.example.videbank.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String country;
    private List<AccountDto> accounts;

    public static CustomerDto fromEntity(Customer customer) {
        List<AccountDto> accountDtos = customer.getAccounts().stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());

        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .country(customer.getCountry())
                .accounts(accountDtos)
                .build();
    }

    public Customer toEntity() {
        List<Account> accountEntities = accounts.stream()
                .map(AccountDto::toEntity)
                .collect(Collectors.toList());

        return Customer.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .country(country)
                .accounts(accountEntities)
                .build();
    }
}
