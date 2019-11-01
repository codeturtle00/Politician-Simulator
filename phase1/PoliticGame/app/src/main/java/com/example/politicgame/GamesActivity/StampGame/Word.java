package com.example.politicgame.GamesActivity.StampGame;

public class Word {
    private String value;
    private int category;

    public Word(String value, int category) {
        this.value = value;
        this.category = category;
    }

    public String getString() {
        return this.value;
    }

    public int getCategory() {
        return this.category;
    }


}
