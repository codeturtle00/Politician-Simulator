package com.example.politicgame.UserActivity.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.politicgame.Games.BabyGame.BabyActivity;
import com.example.politicgame.GameActivity;
import com.example.politicgame.Leaderboard.LeaderBoardActivity;
import com.example.politicgame.LoadCharacter.LoadCharacterActivity;
import com.example.politicgame.R;
import com.example.politicgame.SettingsActivity;
/** An activity displayed for user after user login in
 *
 * CURRENTLY USELESS AS FUNCTIONALITY HAS MERGED INTO MAINACTIVITY*/
public class LoggedInActivity extends GameActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        setTitle("Logged in");

        //Select Characters button
        final Button selectCharactersButton = findViewById(R.id.logged_in_activity_select_char);
        selectCharactersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLoadCharacter();
            }
        });


        //Leader board button, opens the leader board
        final Button boardButton = findViewById(R.id.leader_board_logged);
        boardButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        openLeaderBoard();
                    }
                });


        //Settings button, opens the settings menu
        final Button settingButton = findViewById(R.id.settings);
        settingButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        openSettings();
                    }
                });

        //Sign out button, lead back to main menu
        final Button signOutButton = findViewById(R.id.logged_in_sign_out);
        signOutButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        openMainMenu();
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

    public void openLeaderBoard() {
        /**
         * Opens the leaderboard screen
         */
        Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
        switchBoardIntent.putExtra("SESSION_ID", "loggedIn");
        startActivity(switchBoardIntent);
        finish();
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
