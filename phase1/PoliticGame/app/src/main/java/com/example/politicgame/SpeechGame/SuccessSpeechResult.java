package com.example.politicgame.SpeechGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.politicgame.R;

public class SuccessSpeechResult extends SpeechResult{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_speech_result);
    }
    protected void onStart() {
        getRating().awardPoints();
        super.onStart();
    }
}
