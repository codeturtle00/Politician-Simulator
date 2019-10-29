package com.example.politicgame.SpeechGame;

public class SpeechAwardPoints {
    private final int POINTSGIVEN = 3;
    private int currentPoints;
    public SpeechAwardPoints(int rating){
        currentPoints = rating;
    }


    public void awardPoints(){
        if (currentPoints <= 100 - POINTSGIVEN)
            currentPoints += POINTSGIVEN;
    }

    public void losePoints() {
        if (currentPoints >= POINTSGIVEN)
            currentPoints -= POINTSGIVEN;
    }

    public Integer getCurrentPoints() {
        return new Integer(currentPoints);
    }


}
