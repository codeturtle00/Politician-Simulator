package com.example.politicgame.Games.SpeechGame;

import android.os.Bundle;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;

public class SuccessSpeechResult extends SpeechResult{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_speech_result);

        setTitle("Good job!");
    }

}
