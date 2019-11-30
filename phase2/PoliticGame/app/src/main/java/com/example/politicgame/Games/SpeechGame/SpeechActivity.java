package com.example.politicgame.Games.SpeechGame;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.politicgame.GameActivity;
import com.example.politicgame.MainActivity;
import com.example.politicgame.Pausing.PauseActivity;
import com.example.politicgame.Pausing.PauseButton;
import com.example.politicgame.R;
import com.example.politicgame.Games.StampGame.StampInstructionActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class SpeechActivity extends GameActivity {
    private SpeechPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        setTitle("The Speech Game");

        new PauseButton((ConstraintLayout) findViewById(R.id.speechLayout), this);
    }

    private void update(){
        ArrayList<String> choices = presenter.loadChoice();
        // TextView for prompt and choices
        TextView prompt = findViewById(R.id.prompt);
        prompt.setText(presenter.loadPrompt());
        TextView choiceA = findViewById(R.id.ChoiceA);
        TextView choiceB = findViewById(R.id.ChoiceB);
        TextView choiceC = findViewById(R.id.ChoiceC);
        TextView choiceD = findViewById(R.id.ChoiceD);
        TextView[] textViews = {choiceA, choiceB, choiceC, choiceD};
        for (int i = 0; i < 4; i++) {
            textViews[i].setText(choices.get(i));
        }
        EditText editText = findViewById(R.id.answer);
        editText.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter = (SpeechPresenter) this.getIntent().getSerializableExtra("SPEECH PRESENTER");
        update();
    }

    /**
     * This method is called when user click the compare button Compare the user input with the
     * correct answer If the user input matches the answer, add points and go to successful result
     * page If the user input does not match the answer,keep the point and go to fail result page
     */
    public void compare(View view) {
        EditText editText = findViewById(R.id.answer);
        presenter.setUserInput(editText.getText().toString());

        boolean exit = presenter.isExitPoint();
        if (exit) {
            saveGame(presenter.getCurRating(), "LEVEL2");
        }
        presenter.updateRating();

        Log.i("gm == null", String.valueOf(getIntent().getSerializableExtra("GameMode") == null));

        if (presenter.matches()) {
            Intent successfulIntent = new Intent(this, SuccessSpeechResult.class);
            successfulIntent.putExtra("SPEECH PRESENTER", presenter); // pass the presenter
            successfulIntent.putExtra("GameMode",getIntent().getSerializableExtra("GameMode"));
            startActivityForResult(successfulIntent, 5);
            //finish();
        } else {
            Intent failIntent = new Intent(this, FailureSpeechResult.class);
            failIntent.putExtra("SPEECH PRESENTER", presenter); // pass the presenter
            failIntent.putExtra("GameMode",getIntent().getSerializableExtra("GameMode"));
            startActivityForResult(failIntent, 5);
            //finish();
        }
    }


    public void openMainMenu() {
        Intent mainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(mainMenuIntent);
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
