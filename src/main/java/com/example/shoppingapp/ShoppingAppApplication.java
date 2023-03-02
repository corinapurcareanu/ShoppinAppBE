package com.example.shoppingapp;

import com.example.shoppingapp.configuration.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@SpringBootApplication
//@Import(WebSecurityConfiguration.class)
public class ShoppingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingAppApplication.class, args);
    }

}
