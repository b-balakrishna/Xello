package com.example.demo.entities;

import java.time.LocalDateTime;

public class BoardMember {
    private final Board board;
    private final User user;
    private final LocalDateTime createdAt;

    public BoardMember(Board board, User user) {
        this.board = board;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public Long getBoardId() {
        return board.getId();
    }

    public Long getUserId() {
        return user.getId();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // hashcode and equals generated using both board and user to ensure uniqueness of entity.
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((board == null) ? 0 : board.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BoardMember other = (BoardMember) obj;
        if (board == null) {
            if (other.board != null)
                return false;
        } else if (!board.equals(other.board))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BoardMember [board=" + board.getId() + ", user=" + user.getId() + "]";
    }
}
