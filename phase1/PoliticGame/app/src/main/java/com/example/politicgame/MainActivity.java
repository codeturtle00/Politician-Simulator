package com.example.politicgame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {
    private final String FILE_NAME = "user_game_data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button playButton = findViewById(R.id.start);
        playButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openBabyGame();
                    }
                });


        final Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openLoginPage();
                    }
                });


        final Button boardButton = findViewById(R.id.leaderBoard);
        boardButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openLeaderBoard();
                    }
                });

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
            Log.i("File Status", "The file does not exist yet");
            createFile();
        }
    }

    public boolean fileExists(){
        /**
         * Checks if the file exists in the directory the game will be saved in
         */

        return (new File(FILE_NAME)).exists();
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
        Intent switchBabyIntent = new Intent(this, BabyActivity.class);
        startActivity(switchBabyIntent);
    }

    public void openLoginPage() {
        Intent loginPageIntent = new Intent(this, LoginActivity.class);
        startActivity(loginPageIntent);
    }

    public void openLeaderBoard() {
        Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
        startActivity(switchBoardIntent);
    }

    public void openSettings() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
