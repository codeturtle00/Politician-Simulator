package com.example.politicgame.GamesActivity.SpeechGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.GamesActivity.StampGame.StampInstructionActivity;
import com.example.politicgame.R;

public class SpeechResult extends AppCompatActivity {


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String message = intent.getStringExtra(SpeechActivity.INPUT_MESSAGE);
        TextView textView = findViewById(R.id.userInput);
        textView.setText(message);

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

        final Button button = findViewById(R.id.speechNext);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        openStampGame();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_result);
    }

    public void returnSpeech(){
        Intent backToSpeech = new Intent(this, SpeechActivity.class);
        backToSpeech.putExtra("current rating", SpeechAwardPoints.getCurrentPoints());
        startActivity(backToSpeech);
        finish();
    }

    public void openStampGame() {
        Intent switchStampIntent = new Intent(this, StampInstructionActivity.class);
        startActivity(switchStampIntent);
        finish();
    }

}
