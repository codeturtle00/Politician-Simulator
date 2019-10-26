package com.example.politicgame.StampGame;

import com.example.politicgame.StampGame.Word;

public class Noun extends Word {
    private boolean amountable;

    public Noun(String value, char category, boolean amountable){
        super(value, category);
        this.amountable = amountable;
    }
}
