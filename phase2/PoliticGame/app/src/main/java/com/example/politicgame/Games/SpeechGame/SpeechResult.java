package com.example.politicgame.Games.SpeechGame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.GameActivity;
import com.example.politicgame.Games.StampGame.StampInstructionActivity;
import com.example.politicgame.R;

public class SpeechResult extends GameActivity {

    private final String LEVEL_NAME = "LEVEL2";
    private int num_prompts;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String message = intent.getStringExtra(SpeechActivity.INPUT_MESSAGE);
        TextView textView = findViewById(R.id.userInput);
        textView.setText(message);

        num_prompts = getIntent().getIntExtra("prompt", 1);

        TextView ratingDisplay = findViewById(R.id.curRating);
        String newRating = SpeechAwardPoints.getFeedback();
        ratingDisplay.setText(newRating);
        final Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        returnSpeech();
                    }
                });

        final Button next = findViewById(R.id.speechNext);
        next.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        openStampGame();
                    }
                });
        if(getIntent().getBooleanExtra("visible", false)){
            next.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_result);
    }

    public void returnSpeech(){
        Intent backToSpeech = new Intent(this, SpeechActivity.class);
        backToSpeech.putExtra("current rating", SpeechAwardPoints.getCurrentPoints());
        backToSpeech.putExtra("num prompts", num_prompts);

        Log.i("Current iteration", ((Integer)num_prompts).toString());

        startActivity(backToSpeech);
        finish();
    }

    public void openStampGame() {
        Intent switchStampIntent = new Intent(this, StampInstructionActivity.class);
        saveGame(SpeechAwardPoints.getCurrentPoints(), LEVEL_NAME);
        startActivity(switchStampIntent);
        finish();
    }

}
