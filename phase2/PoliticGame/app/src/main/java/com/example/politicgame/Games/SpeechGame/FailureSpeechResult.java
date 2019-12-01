package com.example.politicgame.Games.SpeechGame;

import android.os.Bundle;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;

/**
 * Inherits from the SpeechResults activity, specialized for when the user inputs an incorrect
 * answer
 */
public class FailureSpeechResult extends SpeechResult {


    /**
     * Initializes result screen for an incorrect answer
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_speech_result);

        setTitle("Oh no...");
    }


}
