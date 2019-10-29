package com.example.politicgame.SpeechGame;

public class SpeechAwardPoints {
    private final int POINTSGIVEN = 3;
    private int currentPoints;
    public SpeechAwardPoints(int rating){
        currentPoints = rating;
    }

    public void awardPoints(){
        currentPoints += POINTSGIVEN;
    }

    public void losePoints() {
        currentPoints -= POINTSGIVEN;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }


}
