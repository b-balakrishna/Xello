package com.example.demo.entities;

import java.time.LocalDateTime;

public class Comment {
	private final Long id;
    private final Card card;
    private final User author;
    private String text;
    private final LocalDateTime createdAt;

    public Comment(Card card, User author, String text){
		this.id = null;
        this.author = author;
        this.card = card;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }

	public Comment(Long id, Comment other){
		this.id = id;
		this.author = other.author;
		this.card = other.card;
		this.text = other.text;
		this.createdAt = other.createdAt;
	}

	public Long getId() {
        return id;
    }

	public Long getAuthorId() {
		return author.getId();
	}

	public Long getCardId() {
		return card.getId();
	}

	public String getText() {
		return text;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
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
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + "]";
	}
}
