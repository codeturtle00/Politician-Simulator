package com.example.politicgame.Games.SpeechGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.GameActivity;
import com.example.politicgame.GameMode.GameMode;
import com.example.politicgame.GameMode.StampArcade;
import com.example.politicgame.Games.StampGame.StampInstructionActivity;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;

/**
 * Activity displaying the instructions for the game
 */
public class SpeechInstructionActivity extends GameActivity {
    private SpeechPresenter presenter = new SpeechPresenter();

    @Override
    /**
     * Initializes this activity
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (((GameMode)getIntent().getSerializableExtra("GameMode")).isGameComplete(app)) {
            openStampGame();
        }

        setContentView(R.layout.activity_speech_instruction);

        setTitle("The Speech Game Instructions");

        final Button button = findViewById(R.id.start_game);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        startSpeechGame();
                    }
                });
    }

    /**
     * Starts SpeechActivity
     */
    public void startSpeechGame() {
        Intent startSpeechIntent = new Intent(this, SpeechActivity.class);

        // Added for game mode modification
        startSpeechIntent.putExtra("GameMode", getIntent().getSerializableExtra("GameMode"));
        startSpeechIntent.putExtra("SPEECH PRESENTER", presenter); // pass the presenter

        startActivity(startSpeechIntent);
        finish();
    }

    /**
     * Switch to the next game's activity
     **/
    public void openStampGame() {
        Intent switchStampIntent = new Intent(this, StampInstructionActivity.class);

        // Added for game mode modification
        switchStampIntent.putExtra("GameMode", new StampArcade(/*app*/));

        startActivity(switchStampIntent);
        finish();
    }
}
