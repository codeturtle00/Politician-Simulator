package com.example.politicgame.Games.SpeechGame;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.io.Serializable;
import java.util.ArrayList;

public class SpeechGameViewModel extends ViewModel implements Serializable {
    private final int STARTRATING = 0;
    private SpeechRepository speechRepo;
    private SpeechAwardPoints rating;
    private boolean exitPoint = false;


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

    boolean isExitPoint() {
        return exitPoint;
    }

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

    public SpeechGameViewModel() {

        this.speechRepo = new SpeechRepository();
        this.rating = new SpeechAwardPoints(STARTRATING);
    }

    void updateRating(boolean win) {
        if (win) {
            rating.awardPoints();
        } else {
            rating.losePoints();
        }
    }

    String getFeedback() {
        String feedback = rating.getFeedback();
        System.out.println("SPEECH VIEW MODEL feedback is " + feedback);
        return feedback;
    }

    int getCurRating() {
        return rating.getCurrentPoints();
    }

    private final int ROUNDNUM = 6;

    public void loadQuestions() {
        this.speechRepo.loadQuestions(ROUNDNUM);
    }
}
