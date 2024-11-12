package com.grupo1.demo.repositories;

import com.grupo1.demo.models.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
  
    Optional<User> findByUsername(String username);
    List<User> findAllByOrderByIdDesc();

    
    @Query("SELECT u FROM User u WHERE LOWER(REPLACE(u.firstName, ' ', '')) LIKE LOWER(REPLACE(:searchTerm, ' ', ''))" +
            " OR LOWER(REPLACE(u.lastName, ' ', '')) LIKE LOWER(REPLACE(:searchTerm, ' ', ''))")
    List<User> findByFirstNameOrLastNameIgnorePunctuationAndCase(String searchTerm);

}
