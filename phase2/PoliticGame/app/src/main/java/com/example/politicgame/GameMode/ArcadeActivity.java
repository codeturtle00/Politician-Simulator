package com.example.politicgame.GameMode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.politicgame.Character.SpriteSetter;
import com.example.politicgame.GameActivity;
import com.example.politicgame.GameMode.GameModeModel.BabyArcade;
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

    /**
     * Start the GameModeActivity activity
     */
    private void startGameModeSelection() {
        Intent gameModeSelectIntent = new Intent(this, GameModeActivity.class);
        startActivity(gameModeSelectIntent);
        finish();
    }

    /**
     * Start a new Arcade Mode and reset level data in case there is existing data
     */
    private void startNewArcadeMode(){
        resetData();

        startBabyGame();
    }

    /**
     * Start the baby game with the Arcade GameMode passedx through
     */
    private void startBabyGame(){
        Intent startArcadeGame = new Intent(this, BabyGameInstruction.class);
        startArcadeGame.putExtra("GameMode", new BabyArcade());
        startActivity(startArcadeGame);
        finish();
    }

    /**
     * Uses an instance of SaveInfo reset the level data
     */
    private void resetData(){
        SaveInfo saveData = new SaveInfo(app.getCurrentUser(), app.getCurrentCharacter(), 0);
        saveData.resetLevels();
    }

    /**
     * Checks if there is an existing playthrough of an Arcade game mode
     * @return  Is there an existing playthrough of the Arcade game mode?
     */
    private boolean existingArcade(){
        return (new BabyArcade()).isGameComplete(app);
    }
}
