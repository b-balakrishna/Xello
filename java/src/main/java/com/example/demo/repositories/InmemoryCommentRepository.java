package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.entities.Comment;

public class InmemoryCommentRepository implements CommentRepository{

    private Map<Long, Comment> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Comment save(Comment entity) {
        Comment comment = new Comment(idCounter.getAndIncrement(),entity);
        storage.putIfAbsent(comment.getId(), comment);
        return comment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Comment> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    } 
}
