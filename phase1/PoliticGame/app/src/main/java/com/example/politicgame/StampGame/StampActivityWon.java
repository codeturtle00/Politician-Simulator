package com.example.politicgame.StampGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;

public class StampActivityWon extends AppCompatActivity {
    protected PoliticGameApp app;

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
        setContentView(R.layout.activity_stamp_won);
    }
}
