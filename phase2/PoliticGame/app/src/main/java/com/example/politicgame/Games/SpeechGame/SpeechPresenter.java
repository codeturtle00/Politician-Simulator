package com.example.politicgame.Games.SpeechGame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Handles actions from SpeechActivity and updates it as required
 */
class SpeechPresenter implements Serializable {
    private String userInput;
    private SpeechGameViewModel speechView;
    private String answer;

    /**
     * Constructor for the SpeechPresenter
     * Initializes a SpeechViewModelObject
     */
    SpeechPresenter() {
        System.out.println("SPEECH PRESENTER constructor should not be called more than once");
        speechView = new SpeechGameViewModel();
        speechView.loadQuestions();
    }

    /**Retrieves the prompts from the SpeechRepository object
     *
     * @return speechView.loadPrompt(): a string containing the prompts
     */
    String loadPrompt() {
        answer = speechView.loadAnswer();
        System.out.println("SPEECH PRESENTER answer is " + answer);
        return speechView.loadPrompt();
    }

    boolean isExitPoint() {
        boolean exit = speechView.isExitPoint();
        System.out.println("SPEECH PRESENTER exit is " + exit);
        return exit;
    }

    /**Retrieves the choices from the SpeechRepository object
     *
     * @return speechView.loadChoice: an ArrayList containing the choices for the user to choose from
     */
    ArrayList<String> loadChoice() {
        return speechView.loadChoice();
    }

    /**Retrieves the current number of points the player currently has
     *
     * @return speechView.getCurRating():int representing the player's points
     */
    int getCurRating() {
        return speechView.getCurRating();
    }

    /**Takes in the user input from the activity
     *
     * @param userInput: string representing the input from the user
     */
    void setUserInput(String userInput) {
        System.out.println("SPEECH PRESENTER user input set to " + userInput);
        this.userInput = userInput;
    }

    /**Returns the user input
     *
     * @return userInput: string representing the user input
     */
    String getUserInput() {
        System.out.println("SPEECH PRESENTER user input is " + userInput);
        return userInput;
    }

    /**Checks if userInput matches the correct answer
     *
     * @return match: boolean representing if the user input and the answer are the same
     */
    Boolean matches() {
        boolean match = userInput.toLowerCase().equals(answer.toLowerCase());
        return match;
    }

    /**Retrieves the feedback from the SpeechViewModel object
     *
     * @return speechView.getFeedBack(): a string containing the feedback for the user's latest
     * answer
     */
    String getFeedback() {
        return speechView.getFeedback();
    }

    /**
     * Updates the points tracked in the SpeechAwardsPoints object depending if the user answered
     * correctly or not
     */
    void updateRating() {
        System.out.println("SPEECH PRESENTER matches is " + matches());
        speechView.updateRating(matches());
    }


}
