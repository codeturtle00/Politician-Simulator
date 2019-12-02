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
    private SpeechTimer timer;
    private int num= 5;

    /**
     * Initializes the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        timer = new SpeechTimer();
        setTitle("The Speech Game");

        new PauseButton((ConstraintLayout) findViewById(R.id.speechLayout), this);
    }
    @Override
    public void onResume(){
        super.onResume();
    if (this.timer.getTimes() >= this.num) {
        openMainMenu();
        }
    }

    /**
     * Helper method to update the prompt and answers every time the user enters an input
     */
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


    /**
     * Initializes the SpeechPresenter object and regularly updates the prompts and answer choices
     */
    @Override
    protected void onStart() {
        super.onStart();
        presenter = (SpeechPresenter) this.getIntent().getSerializableExtra("SPEECH PRESENTER");
        update();
    }

    /**Compares the text of the user's input to the actual answer to decide which result to show
     * the user.
     *
     * @param view
     */
    public void compare(View view) {
        EditText editText = findViewById(R.id.answer);
        presenter.setUserInput(editText.getText().toString());
        presenter.updateRating();
        Log.i("gm == null", String.valueOf(getIntent().getSerializableExtra("GameMode") == null));
        this.timer.addTimes();
        if (presenter.matches()) {
            Intent successfulIntent = new Intent(this, SuccessSpeechResult.class);
            successfulIntent.putExtra("SPEECH PRESENTER", presenter); // pass the presenter
            successfulIntent.putExtra("GameMode",getIntent().getSerializableExtra("GameMode"));
            startActivityForResult(successfulIntent, 5);
        } else {
            Intent failIntent = new Intent(this, FailureSpeechResult.class);
            failIntent.putExtra("SPEECH PRESENTER", presenter); // pass the presenter
            failIntent.putExtra("GameMode",getIntent().getSerializableExtra("GameMode"));
            startActivityForResult(failIntent, 5);
        }
    }

    /**
     * Goes back to the main menu
     */
    public void openMainMenu() {
        Intent mainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(mainMenuIntent);
        finish();
    }
}
