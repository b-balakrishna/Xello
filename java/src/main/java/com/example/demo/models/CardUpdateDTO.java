package com.example.demo.models;

public class CardUpdateDTO {
    private String newTitle;
    private String newDescription;

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        if(!newTitle.equals("null")){
            this.newTitle = newTitle;
        }
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        if(!newDescription.equals("null")){
            this.newDescription = newDescription;
        }
    }
}

