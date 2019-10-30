package com.example.politicgame;

import android.bluetooth.BluetoothA2dp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.politicgame.Common.FileSavingService;


public class SettingsActivity extends AppCompatActivity {
    protected PoliticGameApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (PoliticGameApp) getApplication();

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        final RadioButton radioBlue = findViewById(R.id.colorBlue);
        radioBlue.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        app.chooseBlueTheme(true);
                        restart();
                    }
                });


        final RadioButton radioRed = findViewById(R.id.colorRed);
        radioRed.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        app.chooseBlueTheme(false);
                        restart();
                    }
                });

        final Button quitButton = findViewById(R.id.quit);
        quitButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        returnMainMenu();
                    }
                });
    }

    public void restart (){
        Intent restart = new Intent(this, SettingsActivity.class);
        startActivity(restart);
        finish();
    }

    public void returnMainMenu (){
        Intent returnMainMenu = new Intent(this, MainActivity.class);
        startActivity(returnMainMenu);
        finish();
    }
}
