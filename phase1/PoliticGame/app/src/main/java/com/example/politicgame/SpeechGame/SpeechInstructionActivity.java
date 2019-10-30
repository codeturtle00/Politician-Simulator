package com.example.politicgame.SpeechGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.StampGame.StampActivity;

public class SpeechInstructionActivity extends AppCompatActivity {

    private PoliticGameApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

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
        startSpeechIntent.putExtra("current rating", 0);
        startActivity(startSpeechIntent);
        finish();
    }
}
