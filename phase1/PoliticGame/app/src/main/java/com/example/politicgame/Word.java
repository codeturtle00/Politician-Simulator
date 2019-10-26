package com.example.politicgame;

public class Word {
    private String value;
    private int points;

    public Word(String value, int points){
        this.value = value;
        this.points = points;
    }

    public String getString(){
        return this.value;
    }

    public int getPoints(){
        return this.points;
    }
}
