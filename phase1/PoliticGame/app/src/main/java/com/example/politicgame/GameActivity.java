package com.example.politicgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

/**
 * GameActivity includes code every minigame activity should have.
 * To implement into a minigame's activity, extend GameActivity instead of AppCompatActivity.
 */
public abstract class GameActivity extends AppCompatActivity {

    protected PoliticGameApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }
    }

    public void openMainMenu() {
        Intent mainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(mainMenuIntent);
        finish();
    }

    public void openLeaderBoard() {
        Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
        startActivity(switchBoardIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // requestCode refers to the request code parameter of openPauseMenu's startActivityForResult
        // call
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int userInput = data.getIntExtra("result", 0);

                if (userInput == 1) {
                    Log.i("Pause Result", "User has decided to resume play");
                } else if (userInput == 2) {
                    Log.i("Pause Result", "User has decided to quit the game");
                    openMainMenu();
                }
            } else {
                Log.i("Result Code", "Result code is " + resultCode);
            }
        }
    }
}
