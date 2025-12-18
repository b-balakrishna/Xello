package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Column;

public interface ColumnRepository {
    Column save(Column column);
    Optional<Column> findById(Long id);
    List<Column> findAll();
    void deleteById(Long id);
    Column findByCardId(Long cardId);
}
