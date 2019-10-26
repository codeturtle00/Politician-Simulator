package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.politicgame.StampGame.StampInstructionActivity;

public class SpeechActivity extends AppCompatActivity {
    public static final String INPUT_MESSAGE = "politicgame.speech.result.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        final Button button = findViewById(R.id.speechNext);

        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openStampGame();
                    }
                });
    }

    public void openStampGame() {
        Intent switchStampIntent = new Intent(this, StampInstructionActivity.class);
        startActivity(switchStampIntent);
    }

    /**
     * Compare the user input with the answer
     * If the user input matches the answer, add points and show successful hints
     * If the user input does not match the answer, keep the point and show the right answer*/
    public void compare(View view) {
        Intent intent = new Intent(this, SpeechResult.class);
        EditText editText = (EditText) findViewById(R.id.answer);
        String userInput = editText.getText().toString();
        intent.putExtra(INPUT_MESSAGE, userInput);
        startActivity(intent);

    }

}