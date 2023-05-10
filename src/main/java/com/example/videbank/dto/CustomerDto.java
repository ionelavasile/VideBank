package com.example.videbank.dto;

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
        return null;
    }

    public Customer toEntity() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setCountry(country);
        customer.setAccounts(accounts.stream()
                .map(AccountDto::toEntity)
                .collect(Collectors.toList()));
        return customer;
    }

}

