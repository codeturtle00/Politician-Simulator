package com.example.politicgame.SpeechGame;

public class SpeechAwardPoints {
    private final int POINTSGIVEN = 1;
    private int currentPoints;
    public SpeechAwardPoints(){
        currentPoints = 0;
    }
    public void awardPoints(){
        currentPoints += POINTSGIVEN;
    }


    public int getCurrentPoints() {
        return currentPoints;
    }


}
