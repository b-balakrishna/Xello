package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Card;

public interface CardRepository {
    Card save(Card card);
    Optional<Card> findById(Long id);
    List<Card> findAll();
    void deleteById(Long id);  
}
