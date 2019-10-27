package com.example.politicgame.SpeechGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.R;
import com.example.politicgame.StampGame.StampActivity;

public class SpeechInstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_instruction);
        final Button button = findViewById(R.id.start_game);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        startSpeechGame();
                    }
                });
    }

    public void startSpeechGame() {
        Intent startSpeechIntent = new Intent(this, SpeechActivity.class);
        startActivity(startSpeechIntent);
    }
}
