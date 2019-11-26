package com.example.politicgame.GameMode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.politicgame.Character.SpriteSetter;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.GameActivity;
import com.example.politicgame.GameEnd.SaveInfo;
import com.example.politicgame.Games.BabyGame.BabyGameInstruction;
import com.example.politicgame.R;

public class ArcadeActivity extends GameActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcade_menu);


        // New Game Button, starts a new game
        final Button newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startNewArcadeMode();
                    }
                });

        // Continue Button, starts a new game
        final Button continueButton = findViewById(R.id.continue_button);

        // Disable the continue button if there is no existing play-through
        if (existingArcade()){
            continueButton.setEnabled(true);
            continueButton.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            startBabyGame();
                        }
                    });
        }
        else {
            continueButton.setEnabled(false);
        }

        // Back Button, go back to the game mode selection screen
        final Button goBackButton = findViewById(R.id.back_arcade_menu);
        goBackButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startGameModeSelection();
                    }
                });

        // Set the sprite for the game menu
        final ImageView pauseImage = findViewById(R.id.sprite_arcade_menu);
        SpriteSetter ss = new SpriteSetter(app);
        ss.setSprite(pauseImage);
    }

    public void startGameModeSelection() {
        Intent gameModeSelectIntent = new Intent(this, GameModeActivity.class);
        startActivity(gameModeSelectIntent);
        finish();
    }

    public void startNewArcadeMode(){
        resetData();

        startBabyGame();
    }

    public void startBabyGame(){
        Intent startArcadeGame = new Intent(this, BabyGameInstruction.class);
        startArcadeGame.putExtra("GameMode", new BabyArcade(/*app*/));
        startActivity(startArcadeGame);
        finish();
    }

    public void resetData(){
        SaveInfo saveData = new SaveInfo(app.getCurrentUser(), app.getCurrentCharacter(), 0);
        saveData.resetLevels();
    }

    public boolean existingArcade(){
        return (new BabyArcade()).isGameComplete(app);
    }
}
