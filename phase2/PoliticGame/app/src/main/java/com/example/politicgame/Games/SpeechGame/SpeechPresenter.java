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

    ArrayList<String> loadChoice() {
        return speechView.loadChoice();
    }

    int getCurRating() {
        return speechView.getCurRating();
    }

    void setUserInput(String userInput) {
        System.out.println("SPEECH PRESENTER user input set to " + userInput);
        this.userInput = userInput;
    }

    String getUserInput() {
        System.out.println("SPEECH PRESENTER user input is " + userInput);
        return userInput;
    }

    Boolean matches() {
        boolean match = userInput.toLowerCase().equals(answer.toLowerCase());
        return match;
    }

    String getFeedback() {
        return speechView.getFeedback();
    }

    void updateRating() {
        System.out.println("SPEECH PRESENTER matches is " + matches());
        speechView.updateRating(matches());
    }


}
