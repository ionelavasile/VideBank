package com.example.videbank.mapper;

import com.example.videbank.dto.CustomerDto;
import com.example.videbank.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@Lazy
//To delay the initialization until it is actually used.
// This can help break the circular dependency chain.
public class CustomerMapper {

    private AccountMapper accountMapper;

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .country(customer.getCountry())
                .accounts(accountMapper.toDtoList(customer.getAccounts()))
                .build();
    }

    public Customer toEntity(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .phone(customerDto.getPhone())
                .country(customerDto.getCountry())
                .accounts(accountMapper.toEntityList(customerDto.getAccounts()))
                .build();
    }
    public List<CustomerDto> toDtoList(List<Customer> customers) {
        return customers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
