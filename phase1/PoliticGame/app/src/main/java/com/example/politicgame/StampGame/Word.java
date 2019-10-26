package com.example.politicgame.StampGame;

public class Word {
    private String value;
    private int points;
    private String category;

    public Word(String value, int points, String category) {
        this.value = value;
        this.points = points;
        this.category = category;
    }

    public String getString() {
        return this.value;
    }

    public int getPoints() {
        return this.points;
    }

    public String getcategory() {
        return this.category;
    }


}
