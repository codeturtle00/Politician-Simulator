package com.example.politicgame.Games.SpeechGame;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Responsible keeping track of data processing objects and releasing information to
 * SpeechPresenter
 */
public class SpeechGameViewModel extends ViewModel implements Serializable {
    private final int STARTRATING = 0;
    private SpeechRepository speechRepo;
    private SpeechAwardPoints rating;
    private boolean exitPoint = false;

    /**Retrieves the prompts from the SpeechRepository object
     *
     * @return displayPrompt: a string containing the prompts
     */
    String loadPrompt() {
        String displayPrompt = new String();
        if (this.speechRepo.getPrompt().size() > 0) {
            displayPrompt = this.speechRepo.getPrompt().get(0);
            this.speechRepo.getPrompt().remove(displayPrompt);
            if (this.speechRepo.getPrompt().size() == 1) {
                exitPoint = true;
            }
        } else {
            exitPoint = true;
        }
        return displayPrompt;
    }

    /**Checks if there are any remaining prompts to load
     *
     * @return exitPoint: a boolean so decide if the next game should be loaded
     */
    boolean isExitPoint() {
        return exitPoint;
    }

    /**Retrieves the answers from the SpeechRepository object
     *
     * @return displayAnswer: a string containing the correct answer
     */
    String loadAnswer() {
        String displayAnswer = new String();
        if (this.speechRepo.getAnswer().size() > 0) {
            displayAnswer = this.speechRepo.getAnswer().get(0);
            this.speechRepo.getAnswer().remove(displayAnswer);
            if (this.speechRepo.getAnswer().size() == 1) {
                exitPoint = true;
            }
        } else {
            exitPoint = true;
        }
        return displayAnswer;
    }

    /**Retrieves the choices from the SpeechRepository object
     *
     * @return displayChoice: an ArrayList containing the choices for the user to choose from
     */
    ArrayList<String> loadChoice() {
        ArrayList<String> displayChoice = new ArrayList<>();
        if (this.speechRepo.getChoice().size() > 0) {
            displayChoice = this.speechRepo.getChoice().get(0);
            this.speechRepo.getChoice().remove(displayChoice);
            if (this.speechRepo.getChoice().size() == 1) {
                exitPoint = true;
            }
        } else {
            exitPoint = true;
            displayChoice.add("");
            displayChoice.add("");
            displayChoice.add("");
            displayChoice.add("");
        }
        System.out.println("SPEECHVIEWMODEL choices " + displayChoice);
        return displayChoice;
    }

    /**
     * The constructor which initializes the SpeechRepository and SpeechAwardPoints objects
     */
    SpeechGameViewModel() {
        this.speechRepo = new SpeechRepository();
        this.rating = new SpeechAwardPoints(STARTRATING);
    }

    /**Updates the points tracked in the SpeechAwardsPoints object depending if the user answered
     * correctly or not
     *
     * @param win: boolean indicating if the answer is correct
     */
    void updateRating(boolean win) {
        if (win) {
            rating.awardPoints();
        } else {
            rating.losePoints();
        }
    }

    /**Retrieves the feedback from the SpeechAwardPoints object
     *
     * @return feedback: a string containing the feedback for the user's latest answer
     */
    String getFeedback() {
        String feedback = rating.getFeedback();
        System.out.println("SPEECH VIEW MODEL feedback is " + feedback);
        return feedback;
    }

    /**Retrieves the current number of points the player currently has
     *
     * @return rating.getCurrentPoints():int representing the player's points
     */
    int getCurRating() {
        return rating.getCurrentPoints();
    }

    private final int ROUNDNUM = 6;

    /**
     * Decides on the number of prompts the player must answer
     */
    public void loadQuestions() {
        this.speechRepo.loadQuestions(ROUNDNUM);
    }
}
