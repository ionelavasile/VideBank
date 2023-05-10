package com.example.videbank.mapper;

import com.example.videbank.dto.CustomerDto;
import com.example.videbank.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {

    @Autowired
    private static AccountMapper accountMapper;

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

    public static Customer toEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setCountry(customerDto.getCountry());
        customer.setAccounts(accountMapper.toEntityList(customerDto.getAccounts()));
        return customer;
    }


}





