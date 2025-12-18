package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.entities.Column;

public class InmemoryColumnRepository implements ColumnRepository{
    private Map<Long, Column> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Column save(Column entity) {
        if (entity.getId() != null && storage.containsKey(entity.getId())) {
            storage.put(entity.getId(), entity);
            return entity;
        }
        Column column = new Column(idCounter.getAndIncrement(), entity);
        storage.put(column.getId(), column);
        return column;
    }

    @Override
    public Column findByCardId(Long cardId) {
        for (Column column : storage.values()) {
            if (column.getCards().stream().anyMatch(card -> card.getId().equals(cardId))) {
                return column;
            }
        }
        return null;
    }

    @Override
    public Optional<Column> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Column> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }

   
}
