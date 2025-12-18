package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.BoardMember;

public interface BoardMemberRepository {
    BoardMember save(BoardMember boardMember);
    Optional<BoardMember> findById(Long boardId, Long userId);
    List<BoardMember> findAll();
    void deleteById(Long boardId, Long userId);
    //Other relevant repository methods
    Boolean exists(Long boardId, Long userId);
}
