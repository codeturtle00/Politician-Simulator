package com.example.politicgame;

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

public class MainActivity extends GameActivity {
    private final String FILE_NAME = "user_game_data.json";

    protected void onStart() {
        //If the theme is changed from the start menu then this will reflect that change
        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        super.onStart();
        System.out.println("The current theme is blue: " + app.isThemeBlue());
    }

    protected void onResume() {
        //If the theme is changed from the start menu then this will reflect that change
        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        super.onResume();
        System.out.println("The current theme is blue: " + app.isThemeBlue());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTitle("Main Menu");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Play button, starts the game
        final Button playButton = findViewById(R.id.start);
        playButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openBabyGame();
                    }
                });


        //Login button, starts the login process
        final Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openLoginPage();
                    }
                });


        //Leaderboard button, opens the leaderboard
        final Button boardButton = findViewById(R.id.leaderBoard);
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

        //Check if file exists
        if (!fileExists()) {
            Log.i("File Status", "The file does not exist yet at " + getFilesDir() + "/" + FILE_NAME);
            createFile();
        }

        ImageView trumpIMG = findViewById(R.id.trump);
        Animation animated_trump = AnimationUtils.loadAnimation(this, R.anim.animated_trump);
        trumpIMG.startAnimation(animated_trump);
    }

    public boolean fileExists(){
        /**
         * Checks if the file exists in the directory the game will be saved in
         */

        Log.i("File Status", "Checking at " + getFilesDir() + "/" + FILE_NAME);
        return (new File(getFilesDir() + "/" + FILE_NAME)).exists();
    }

    public void createFile(){
        /**
         * Creates the file in the device which we will use to store user data and other persistent
         * game info.
         */

        try {
            FileOutputStream outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            JSONArray fileStructure = new JSONArray();
            JSONObject fillerUsers = generateEmptyLeaderBoard();

            fileStructure.put(fillerUsers);

            outputStream.write(fileStructure.toString().getBytes());
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject generateEmptyLeaderBoard(){
        JSONObject leaderBoard = new JSONObject();

        try{
            leaderBoard.put("Kullen", new JSONObject().put("Kullen the Kreeper", (new JSONObject()).put("highScore", 40)));
            leaderBoard.put("Yitan", new JSONObject().put("Yitan the Titan", (new JSONObject()).put("highScore", 30)));
            leaderBoard.put("Toe-knee", new JSONObject().put("Toe-knee the shoe-in", (new JSONObject()).put("highScore", 10)));
        } catch (JSONException e){
            e.printStackTrace();
        }

        return leaderBoard;
    }

    public void openBabyGame() {
        /**
         * Starts the first game
         */
        Intent switchBabyIntent = new Intent(this, BabyActivity.class);
        startActivity(switchBabyIntent);
        finish();
    }

    public void openLoginPage() {
        /**
         * Opens the login page
         */
        Intent loginPageIntent = new Intent(this, LoginActivity.class);
        startActivity(loginPageIntent);
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
        settingsIntent.putExtra("EXTRA_SESSION_ID", "main");
        startActivity(settingsIntent);
        finish();
    }
}
