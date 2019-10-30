package com.example.politicgame.SpeechGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;

public class FailureSpeechResult extends SpeechResult {
    private PoliticGameApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_speech_result);
    }



}
