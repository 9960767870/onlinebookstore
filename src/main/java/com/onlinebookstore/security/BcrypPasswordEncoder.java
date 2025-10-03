package com.onlinebookstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BcrypPasswordEncoder {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BcrypPasswordEncoder bcrypPasswordEncoder = new BcrypPasswordEncoder();
        System.out.println(bcrypPasswordEncoder.passwordEncoder().encode("rushi"));
    }
}
