package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.politicgame.BabyGame.BabyActivity;
import com.example.politicgame.ui.Login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoggedInActivity extends GameActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        //Select Characters button
        final Button selectCharactersButton = findViewById(R.id.logged_in_activity_select_char);
        selectCharactersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLoadCharacter();
            }
        });


        //Play button, starts the game, ONLY HERE FOR TESTING TODO REMOVE THIS
        final Button playButton = findViewById(R.id.start);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openBabyGame();
            }
        });


        //Leaderboard button, opens the leaderboard
        final Button boardButton = findViewById(R.id.leader_board_logged);
        boardButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openLeaderBoard();
                    }
                });


        //Settings button, opens the settings menu
        final Button settingButton = findViewById(R.id.settings);
        settingButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openSettings();
                    }
                });

        ImageView trumpIMG = findViewById(R.id.bush);
        Animation animated_trump = AnimationUtils.loadAnimation(this, R.anim.animated_trump);
        trumpIMG.startAnimation(animated_trump);
    }

    public void openLoadCharacter () {
        Intent loadCharacters = new Intent(this, LoadCharacterActivity.class);
        startActivity(loadCharacters);
        finish();
    }

    public void openBabyGame() {
        /**
         * Starts the first game
         */
        Intent switchBabyIntent = new Intent(this, BabyActivity.class);
        startActivity(switchBabyIntent);
        finish();
    }


    public void openLeaderBoard() {
        /**
         * Opens the leaderboard screen
         */
        Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
        startActivityForResult(switchBoardIntent, 2);
    }

    public void openSettings() {
        /**
         * Open the settings menu
         */
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        settingsIntent.putExtra("SESSION_ID", "loggedIn");
        startActivity(settingsIntent);
        finish();
    }
}
