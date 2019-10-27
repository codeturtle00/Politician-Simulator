package com.example.politicgame.SpeechGame;

import java.util.HashMap;

public class SpeechGame {

    private int points;
    private String input;
    private String prompt;
    private String relevantWord;
    HashMap<String, String> display = new HashMap<String, String>();
    private static final String EXAMPLE_FILE = "SpeechPrompt.txt";
    private final int POINTSGIVEN = 1;
    private static final String TAG = "Main Activity";

    public void setDisplay(String prompt, String choice ) {
        this.display.put(prompt, choice);
    }

    public HashMap<String, String> getDisplay() {
        return this.display;
    }

    public SpeechGame() {
        this.input = "";
        this.prompt = "";
        this.relevantWord = "";
    }


    public void setInput(String input) {
        this.input = input.toLowerCase();
    }

    private boolean awardPoints() {
        return input.equals(relevantWord);
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }





}
