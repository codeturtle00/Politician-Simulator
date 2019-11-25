package com.example.politicgame.Games.SpeechGame;

import java.io.Serializable;

/**
 * Class responsible for keeping track and awarding points depending on a player's answer
 */
class SpeechAwardPoints implements Serializable {
    private final int POINTSGIVEN = 20;
    private int currentPoints;
    private String feedback;

    /**Constructor for SpeechAwardPoints
     * Initializes current points and feedback
     *
     * @param rating: initial rating (usually set to 0 but may take previous game's score)
     */
    SpeechAwardPoints(int rating){

        currentPoints = rating;
        feedback = "Your current rating is: " + currentPoints + "%";
    }

    /**Getter for currentPoints
     *
     * @return value of current points
     */
    int getCurrentPoints() {
        return currentPoints;
    }

    /**Getter for feedback
     *
     * @return feedback to be displayed on the results page
     */
    String getFeedback() {
        System.out.println("SPEECH AWARD POINTS feedback is " +feedback);
        return feedback;
    }

    /**Adds POINTSGIVEN to currentPoints if player answers correctly
     *
     */
    void awardPoints(){
        if (currentPoints <= 100 - POINTSGIVEN) {
            currentPoints += POINTSGIVEN;
            feedback = "Your current rating is: " + currentPoints + "%";
        }
    }

    /**Takes away POINTSGIVEN to currentPoints if player answers incorrectly
     *
     */
    void losePoints() {
        if (currentPoints >= POINTSGIVEN) {
            currentPoints -= POINTSGIVEN;
            feedback = "Your current rating is: " + currentPoints + "%";
        }
    }



}
