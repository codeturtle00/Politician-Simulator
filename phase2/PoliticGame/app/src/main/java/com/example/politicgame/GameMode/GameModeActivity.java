package com.example.politicgame.GameMode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.GameActivity;
import com.example.politicgame.Games.BabyGame.BabyActivity;
import com.example.politicgame.Games.BabyGame.BabyGameInstruction;
import com.example.politicgame.Games.SpeechGame.SpeechInstructionActivity;
import com.example.politicgame.Games.StampGame.StampInstructionActivity;
import com.example.politicgame.LoadCharacter.LoadCharacterActivity;
import com.example.politicgame.R;

public class GameModeActivity extends GameActivity {
    private final String BABYLEVEL = "LEVEL1";
    private final String SPEECHLEVEL = "LEVEL2";
    private final String STAMPLEVEL = "LEVEL3";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);


        final TextView startArcadeMode = findViewById(R.id.ArcadeMode);
        startArcadeMode.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startArcadeGameMode();
                    }
                });

        final TextView startBabyMode = findViewById(R.id.BabyGameMode);
        startBabyMode.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startBabyGameMode();
                    }
                });

        final TextView startSpeechMode = findViewById(R.id.SpeechGameMode);
        startSpeechMode.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startSpeechGameMode();
                    }
                });

        final TextView startStampMode = findViewById(R.id.StampGameMode);
        startStampMode.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startStampGameMode();
                    }
                });

        final Button backGameMode = findViewById(R.id.back_game_mode);
        backGameMode.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
                    openLoadCharacter();
                }
            });
    }


    private void startArcadeGameMode() {
        Intent startArcadeGame = new Intent(this, BabyGameInstruction.class);
        startArcadeGame.putExtra("GameMode", new BabyArcade(/*app*/));
        startActivity(startArcadeGame);
        finish();
    }


    private void startBabyGameMode() {
        Intent startBabyGame = new Intent(this, BabyGameInstruction.class);
        startBabyGame.putExtra("GameMode", new SingleMode(BABYLEVEL));
        startActivity(startBabyGame);
        finish();
    }


    private void startSpeechGameMode() {
        Intent startSpeechGame = new Intent(this, SpeechInstructionActivity.class);
        startSpeechGame.putExtra("GameMode", new SingleMode(SPEECHLEVEL));
        startActivity(startSpeechGame);
        finish();
    }


    private void startStampGameMode() {
        Intent startStampGame = new Intent(this, StampInstructionActivity.class);
        startStampGame.putExtra("GameMode", new SingleMode(STAMPLEVEL));
        startActivity(startStampGame);
        finish();
    }


    private void openLoadCharacter () {
        Intent loadCharacters = new Intent(this, LoadCharacterActivity.class);
        startActivity(loadCharacters);
        finish();
    }
}
