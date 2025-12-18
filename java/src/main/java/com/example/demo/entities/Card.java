package com.example.demo.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Card {
    private final Long id;
    private String title;
    private String description;
    private final Set<User> members;
    private final List<Comment> comments;  
    private final LocalDateTime createdAt;

    private Column column; // Add a reference to the owning column

    public Card(String title) {
        this.id = null;
        this.title = title;
        this.members = new HashSet<>();
        this.comments = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public Card(Long id, Card other) {
        this.id = id;
        this.title = other.title;
        this.description = other.description;
        this.members = other.members;
        this.comments = other.comments;
        this.createdAt = other.createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<User> getMembers() {
		return new ArrayList<>(members);
	}

    public Column getColumn() {
		return column;
	}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColumn(Column column) {
        if (this.column != column) { // Check if the card is not already in the same column
            this.column = column;
            if (column != null) {
                column.addCard(this); // Add the card to the new column
            }
        }
    }

    public void addMember(User user) {
        members.add(user);
	}

    public void removeMember(User user) {
        members.remove(user);
	}

    public void addComment(Comment comment) {
        comments.add(comment);
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Card other = (Card) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Card [id=" + id + "]";
    }
}
