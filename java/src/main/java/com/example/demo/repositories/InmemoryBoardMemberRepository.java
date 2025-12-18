package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.entities.BoardMember;

public class InmemoryBoardMemberRepository implements BoardMemberRepository{

    // Create a storage map to hold BoardMembers with their unique IDs.
    private Map<String, BoardMember> storage = new HashMap<>();

    @Override
    public BoardMember save(BoardMember boardMember) {
        // Generate a unique ID for the BoardMember.
        String boardMemberId = getBoardMemberId(boardMember.getBoardId(),boardMember.getUserId());
        storage.put(boardMemberId, boardMember);
        return boardMember;
    }

    @Override
    public Optional<BoardMember> findById(Long boardId, Long userId) {
        // Find a BoardMember in the storage map based on boardId and userId.
        return storage.values().stream()
            .filter(v -> v.getBoardId() == boardId && v.getUserId() == userId)
            .findFirst();
    }

    @Override
    public List<BoardMember> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long boardId, Long userId) {
        // Generate the ID for the BoardMember to be deleted.
        String boardMemberId = getBoardMemberId(boardId, userId);
        storage.remove(boardMemberId);
    }

    @Override
    public Boolean exists(Long boardId, Long userId) {
        // Generate the ID for the BoardMember to be searched.
        String boardMemberId = getBoardMemberId(boardId, userId);
        return storage.values().stream()
        .anyMatch(boardMember -> getBoardMemberId(boardMember.getBoardId(),boardMember.getUserId()).equals(boardMemberId));
    }

    private String getBoardMemberId(long boardId, Long userId){
        // Generate a unique ID for a BoardMember in the format: "Board[id] User[id]"
        // This representation makes a BoardMember's ID unique.
        return new StringBuilder().append("Board[").append(boardId)
        .append("] User[").append(userId).append("]").toString();
    }
}
