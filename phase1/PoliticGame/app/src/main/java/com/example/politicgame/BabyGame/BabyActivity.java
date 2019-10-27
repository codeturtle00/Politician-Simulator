package com.example.politicgame.BabyGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.politicgame.R;
import com.example.politicgame.SpeechGame.SpeechActivity;

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
    }

    public void openSpeechGame() {
        Intent switchSpeechIntent = new Intent(this, SpeechActivity.class);
        startActivity(switchSpeechIntent);
    }


}
