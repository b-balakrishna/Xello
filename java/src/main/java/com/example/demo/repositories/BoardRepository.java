package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Board;

public interface BoardRepository {
    Board save(Board board);
    Optional<Board> findById(Long id);
    List<Board> findAll();
    void deleteById(Long id); 
}
