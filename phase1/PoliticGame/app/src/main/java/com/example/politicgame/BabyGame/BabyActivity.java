package com.example.politicgame.BabyGame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.politicgame.MainActivity;
import com.example.politicgame.PauseActivity;
import com.example.politicgame.R;
import com.example.politicgame.SpeechGame.SpeechInstructionActivity;

public class BabyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby);
        final Button button = findViewById(R.id.babyNext);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openSpeechGame();
                    }
                });

        final Button pauseB = findViewById(R.id.pause);
        pauseB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.i("Button", "The pause button has been clicked");

                        //The method below will pause the game and handle the following inputs
                        openPauseMenu();
                    }
                });
    }

    public void openSpeechGame() {
        Intent switchSpeechIntent = new Intent(this, SpeechInstructionActivity.class);
        startActivity(switchSpeechIntent);
    }

    public void openPauseMenu(){
        Intent pauseMenuIntent = new Intent(this, PauseActivity.class);
        startActivityForResult(pauseMenuIntent, 1);
    }

    public void openMainMenu(){
        Intent mainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(mainMenuIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //requestCode refers to the request code parameter of openPauseMenu's startActivityForResult call
        if (requestCode == 1) {
            if (resultCode == RESULT_OK){
                int userInput = data.getIntExtra("result", 0);

                if (userInput == 1){
                    Log.i("Pause Result", "User has decided to resume play");
                }

                else if (userInput == 2){
                    Log.i("Pause Result", "User has decided to quit the game");
                    openMainMenu();
                }
            }

            else {
                Log.i("Result Code", "Result code is " + resultCode);
            }
        }
    }
}
