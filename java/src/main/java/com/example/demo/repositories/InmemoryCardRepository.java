package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.entities.Card;

public class InmemoryCardRepository implements CardRepository {

    private Map<Long, Card> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Card save(Card entity) {
        if (entity.getId() == null) {
            Card newCard = new Card(idCounter.getAndIncrement(), entity);
            storage.put(newCard.getId(), newCard);
            return newCard;
        } else {
            storage.put(entity.getId(), entity);
            return entity;
        }
    }
    

    @Override
    public Optional<Card> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Card> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
    
}
