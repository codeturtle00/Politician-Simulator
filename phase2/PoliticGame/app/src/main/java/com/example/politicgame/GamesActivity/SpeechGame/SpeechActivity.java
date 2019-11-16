package com.example.politicgame.GamesActivity.SpeechGame;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.politicgame.GameActivity;
import com.example.politicgame.MainActivity;
import com.example.politicgame.PauseActivity;
import com.example.politicgame.PauseButton;
import com.example.politicgame.R;
import com.example.politicgame.GamesActivity.StampGame.StampInstructionActivity;

import java.util.ArrayList;

public class SpeechActivity extends GameActivity {
    private static final String TAG = "Speech Activity";
    public static final String INPUT_MESSAGE = "politicgame.speech.input";
    public static final String CORRECTION_MESSAGE = "politicgame.speech.result";
    private final String LEVEL_NAME = "LEVEL2";
    private String correct;
    SpeechAwardPoints rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        String displayPrompt = app.getSpeechView().loadPrompt();
        String answer = app.getSpeechView().loadAnswer();
        this.correct = answer;
        ArrayList<String> choice = app.getSpeechView().loadChoice();


        // TextView for prompt and choices
        TextView prompt = findViewById(R.id.prompt);
        prompt.setText(displayPrompt);
        TextView choiceA = findViewById(R.id.ChoiceA);
        TextView choiceB = findViewById(R.id.ChoiceB);
        TextView choiceC = findViewById(R.id.ChoiceC);
        TextView choiceD = findViewById(R.id.ChoiceD);
        TextView[] textViews = {choiceA, choiceB, choiceC, choiceD};
        for (int i = 0; i < 4; i++) {
            textViews[i].setText(choice.get(i));
        }
        rating = new SpeechAwardPoints(getIntent().getIntExtra("current rating", 0));
        setTitle("The Speech Game");

        new PauseButton((ConstraintLayout) findViewById(R.id.speechLayout), this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * This method is called when user click the compare button Compare the user input with the
     * correct answer If the user input matches the answer, add points and go to successful result
     * page If the user input does not match the answer,keep the point and go to fail result page
     */
    public void compare(View view) {
        EditText editText = (EditText) findViewById(R.id.answer);
        String userInput = editText.getText().toString();
        boolean matches = userInput.toLowerCase().equals(this.correct.toLowerCase());
        boolean exit = app.getSpeechView().isExitPoint();

        if(exit){
            saveGame(SpeechAwardPoints.getCurrentPoints(), LEVEL_NAME);
        }

        if (matches) {
            Intent successfulIntent = new Intent(this, SuccessSpeechResult.class);
            successfulIntent.putExtra(INPUT_MESSAGE, userInput);
            successfulIntent.putExtra("visible", exit);
            startActivity(successfulIntent);
            rating.awardPoints();
            finish();
        } else {
            Intent failIntent = new Intent(this, FailureSpeechResult.class);
            failIntent.putExtra(INPUT_MESSAGE, userInput);
            failIntent.putExtra("visible", exit);
            startActivity(failIntent);
            rating.losePoints();
            finish();
        }
    }

    public void openPauseMenu() {
        Intent pauseMenuIntent = new Intent(this, PauseActivity.class);
        startActivityForResult(pauseMenuIntent, 1);
    }

    public void openMainMenu() {
        Intent mainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(mainMenuIntent);
        finish();
    }


    /* Switch to Next Game View**/
    public void openStampGame() {
        Intent switchStampIntent = new Intent(this, StampInstructionActivity.class);
        startActivity(switchStampIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //requestCode refers to the request code parameter of openPauseMenu's startActivityForResult call
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
