package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.entities.Board;

public class InmemoryBoardRepository implements BoardRepository{

    private Map<Long, Board> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Board save(Board entity) {
        Board board = new Board(idCounter.getAndIncrement(),entity);
        storage.putIfAbsent(board.getId(), board);
        return board;
    }

    @Override
    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
    
}
