package com.example.politicgame.StampGame;

public class Word {
    private String value;
    private char category;

    public Word(String value, char category) {
        this.value = value;
        this.category = category;
    }

    public String getString() {
        return this.value;
    }

    public char getcategory() {
        return this.category;
    }


}
