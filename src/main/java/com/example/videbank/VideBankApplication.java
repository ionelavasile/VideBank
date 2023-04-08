package com.example.videbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class VideBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideBankApplication.class, args);
    }

}
