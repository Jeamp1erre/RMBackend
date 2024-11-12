package com.grupo1.demo.config;

import com.grupo1.demo.models.Role;
import com.grupo1.demo.models.User;
import com.grupo1.demo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initializeAdmin() {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User adminUser = User.builder()
                    .firstName("Admin")
                    .lastName("Admin")
                    .email("admin@example.com")
                    .phone("123456789")
                    .username("admin")
                    .password(passwordEncoder.encode("admin")) 
                    .role(Role.ADMIN)
                    .build();
                
                userRepository.save(adminUser);
                System.out.println("Usuario administrador predeterminado creado con éxito.");
            } else {
                System.out.println("Usuario administrador ya existe.");
            }
        };
    }
}
