package com.example.politicgame.Games.SpeechGame;

import android.os.Bundle;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;

public class FailureSpeechResult extends SpeechResult {
    private PoliticGameApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_speech_result);

        setTitle("Oh no...");
    }



}
