package com.example.politicgame.Games.SpeechGame;

import android.os.Bundle;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;

/**
 * Inherits the SpeechResult activity, specialized for when the user enters the correct answer
 */
public class SuccessSpeechResult extends SpeechResult{

    @Override
    /**
     * Initializes result screen for correct answer
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_speech_result);

        setTitle("Good job!");
    }

}
