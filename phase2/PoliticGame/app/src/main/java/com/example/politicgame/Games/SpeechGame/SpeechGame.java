package com.example.politicgame.Games.SpeechGame;

import java.util.HashMap;

public class SpeechGame {

    HashMap<String, String> display = new HashMap<String, String>();

    public void setDisplay(String prompt, String choice ) {
        this.display.put(prompt, choice);
    }

    public HashMap<String, String> getDisplay() {
        return this.display;
    }

    public SpeechGame() {

    }


}
