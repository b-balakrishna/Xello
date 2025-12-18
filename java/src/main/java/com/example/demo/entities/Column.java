package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Column {
    private final Long id;
    private String name;

    private final Set<Card> cards;

    private Board board; // Add a reference to the owning board

    public Column(String name) {
        this.id = null;
        this.name = name;
        this.cards = new HashSet<>();
    }

    public Column(Long id, Column other){
        this.id = id;
        this.name = other.name;
        this.cards = other.cards;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards(){
        return new ArrayList<>(cards);
    }

    public Board getBoard() {
		return board;
	}

    public Long getBoardId() {
        return board != null ? board.getId() : null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoard(Board board) {
        if(this.board != board){ // Check if the column is not already in the same board
            this.board = board;
            board.addColumn(this);
        }
	}

    public void addCard(Card card){
        if(!cards.contains(card)){ // Check if the card is not already in the column
            cards.add(card);
            card.setColumn(this); // Set the owning column for the card  
        }
    }

    public void removeCard(Card card) {
        if (cards.contains(card)) {
            cards.remove(card);
            card.setColumn(null); // Remove the reference to this column from the card
        }
    }

    public void replaceCard(Card oldCard, Card newCard) {
        cards.remove(oldCard);
        cards.add(newCard);
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
        Column other = (Column) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Column [id=" + id + "]";
    }
}
