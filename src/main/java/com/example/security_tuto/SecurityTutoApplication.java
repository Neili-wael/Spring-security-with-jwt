package com.example.security_tuto;

import com.example.security_tuto.model.AppUser;
import com.example.security_tuto.model.Role;
import com.example.security_tuto.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityTutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityTutoApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

}

