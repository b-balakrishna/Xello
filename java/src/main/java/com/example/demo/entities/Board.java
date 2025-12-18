package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Board {
    private final Long id;
    private String name;
    private final User owner;
    private BoardVisibility boardVisibility;

    private final Set<Column> columns;

    public Board(String name, User owner, BoardVisibility boardVisibility){
        this.id = null;
        this.name = name;
        this.owner = owner;
        this.boardVisibility = boardVisibility;
        this.columns = new HashSet<>();
    }

    public Board (Long id, Board other){
        this.id = id;
        this.name = other.name;
        this.owner = other.owner;
        this.boardVisibility = other.boardVisibility;
        this.columns = other.columns;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public BoardVisibility getVisibility() {
        return boardVisibility;
    }

    public List<Column> getColumns(){
        return new ArrayList<>(columns);
    }

    public void setBoardVisibility(BoardVisibility boardVisibility) {
        this.boardVisibility = boardVisibility;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addColumn(Column column){
        if(!columns.contains(column)){ // Check if the column is not already in the board
            columns.add(column);
            column.setBoard(this); // Set the owning board for the column
        }
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
        Board other = (Board) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Board [id=" + id + "]";
    }
}
