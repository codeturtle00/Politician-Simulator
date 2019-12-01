package com.example.politicgame.GameMode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.GameActivity;
import com.example.politicgame.Games.BabyGame.BabyActivity;
import com.example.politicgame.Games.BabyGame.BabyGameInstruction;
import com.example.politicgame.Games.SpeechGame.SpeechInstructionActivity;
import com.example.politicgame.Games.StampGame.StampInstructionActivity;
import com.example.politicgame.LoadCharacter.LoadCharacterActivity;
import com.example.politicgame.MainActivity;
import com.example.politicgame.R;

public class GameModeActivity extends GameActivity {
    private final String BABYLEVEL = "LEVEL1";
    private final String SPEECHLEVEL = "LEVEL2";
    private final String STAMPLEVEL = "LEVEL3";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);


        // User clicked arcade mode
        final Button startArcadeMode = findViewById(R.id.ArcadeMode);
        startArcadeMode.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startArcadeMenu();
                    }
                });

        // User clicked BabyGame Mode
        final Button startBabyMode = findViewById(R.id.BabyGameMode);
        startBabyMode.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startBabyGameMode();
                    }
                });

        // User clicked SpeechGame Mode
        final Button startSpeechMode = findViewById(R.id.SpeechGameMode);
        startSpeechMode.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startSpeechGameMode();
                    }
                });

        // User clicked StampGame Mode
        final Button startStampMode = findViewById(R.id.StampGameMode);
        startStampMode.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startStampGameMode();
                    }
                });

        // User clicked go back
        final Button backGameMode = findViewById(R.id.back_game_mode);
        backGameMode.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
                    openMainMenu();
                }
            });
    }


    /**
     * Start the Arcade Game Mode, which has the player play each game in the order they were meant
     * to be played, from BabyGame, to SpeechGame and then to StampGame.
     */
    private void startArcadeMenu() {
        Intent startArcadeMenu = new Intent(this, ArcadeActivity.class);
        startActivity(startArcadeMenu);
        finish();
    }


    /**
     * Start the Single-Game Mode and load BabyGame, which will lead the player to a results screen
     * after they are done the game
     */
    private void startBabyGameMode() {
        Intent startBabyGame = new Intent(this, BabyGameInstruction.class);
        startBabyGame.putExtra("GameMode", new SingleMode(BABYLEVEL));
        startActivity(startBabyGame);
        finish();
    }


    /**
     * Start the Single-Game Mode and load SpeechGame, which will lead the player to a results screen
     * after they are done the game
     */
    private void startSpeechGameMode() {
        Intent startSpeechGame = new Intent(this, SpeechInstructionActivity.class);
        startSpeechGame.putExtra("GameMode", new SingleMode(SPEECHLEVEL));
        startActivity(startSpeechGame);
        finish();
    }


    /**
     * Start the Single-Game Mode and load StampGame, which will lead the player to a results screen
     * after they are done the game
     */
    private void startStampGameMode() {
        Intent startStampGame = new Intent(this, StampInstructionActivity.class);
        startStampGame.putExtra("GameMode", new SingleMode(STAMPLEVEL));
        startActivity(startStampGame);
        finish();
    }
}
