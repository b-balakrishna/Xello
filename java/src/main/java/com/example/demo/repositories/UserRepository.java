package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.User;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    //Other relevant repository methods
    Boolean exists(String email);
}
