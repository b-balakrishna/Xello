package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Comment;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findAll();
    void deleteById(Long id);
}
