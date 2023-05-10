package com.example.videbank.utils;

import com.example.videbank.entity.Customer;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CustomFakerCustomer {

    public List<Customer> generateDummyCustomers() {
        List<Customer> customers = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Customer customer = (Customer) Customer.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .country(faker.address().country())
                    .build();
            customers.add(customer);
        }
        return customers;
    }
}

