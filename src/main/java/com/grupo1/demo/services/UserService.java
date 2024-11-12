package com.grupo1.demo.services;

import com.grupo1.demo.models.Role;
import com.grupo1.demo.models.User;
import com.grupo1.demo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new RuntimeException("El nombre de usuario ya está en uso");
            }
    
            if (user.getRole() == null) {
                user.setRole(Role.USER);
            }
    
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }


    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByIdDesc();  
    }

    public List<User> searchUsersByNameOrLastName(String searchTerm) {
        return userRepository.findByFirstNameOrLastNameIgnorePunctuationAndCase(searchTerm);
    }
    
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            
    
            existingUser.setFirstName(
                Optional.ofNullable(userDetails.getFirstName()).orElse(existingUser.getFirstName())
            );
            existingUser.setLastName(
                Optional.ofNullable(userDetails.getLastName()).orElse(existingUser.getLastName())
            );
            existingUser.setEmail(
                Optional.ofNullable(userDetails.getEmail()).orElse(existingUser.getEmail())
            );
            existingUser.setPhone(
                Optional.ofNullable(userDetails.getPhone()).orElse(existingUser.getPhone())
            );
            if (userDetails.getPassword() != null) {
                existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword())); 
            }
            
            return userRepository.save(existingUser);  
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
   
     public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        userRepository.delete(existingUser);
    }


    public User adminUpdateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
    
            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }
    
            existingUser.setFirstName(
                Optional.ofNullable(userDetails.getFirstName()).orElse(existingUser.getFirstName())
            );
            existingUser.setLastName(
                Optional.ofNullable(userDetails.getLastName()).orElse(existingUser.getLastName())
            );
            existingUser.setEmail(
                Optional.ofNullable(userDetails.getEmail()).orElse(existingUser.getEmail())
            );
            existingUser.setPhone(
                Optional.ofNullable(userDetails.getPhone()).orElse(existingUser.getPhone())
            );
            existingUser.setUsername(
                Optional.ofNullable(userDetails.getUsername()).orElse(existingUser.getUsername())
            );
            existingUser.setRole(
                Optional.ofNullable(userDetails.getRole()).orElse(existingUser.getRole())
            );
    
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
}