package com.example.politicgame.Games.SpeechGame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.GameActivity;
import com.example.politicgame.GameMode.GameMode;
import com.example.politicgame.Games.StampGame.StampInstructionActivity;
import com.example.politicgame.R;

import java.io.Serializable;

public class SpeechResult extends GameActivity implements Serializable {
    private SpeechPresenter presenter;
    private final String LEVEL_NAME = "LEVEL2";

    @Override
    protected void onStart() {
        super.onStart();

        presenter = (SpeechPresenter) this.getIntent().getSerializableExtra("SPEECH PRESENTER");

        TextView textView = findViewById(R.id.userInput);
        textView.setText(presenter.getUserInput());

        TextView ratingDisplay = findViewById(R.id.curRating);
        ratingDisplay.setText(presenter.getFeedback());

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

        if (presenter.isExitPoint()) {
            next.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_result);
    }

    public void returnSpeech() {
        finish();
    }

    public void openStampGame() {
        GameMode gm = (GameMode) getIntent().getSerializableExtra("GameMode");

        Log.i("gm == null", String.valueOf(gm == null));
        Log.i("presenter == null", String.valueOf(presenter == null));

        // Save and then move to the next activity
        // Also the if condition will never be false, but this is here to just remove a warning
        if (gm != null) {
            gm.save(app, presenter.getCurRating());
            Intent switchStampIntent = gm.next(this);

            startActivity(switchStampIntent);
            finish();
        }
    }

}
