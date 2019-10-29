package com.example.politicgame;

import android.bluetooth.BluetoothA2dp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.politicgame.Common.FileSavingService;


public class SettingsActivity extends AppCompatActivity {
    private FileSavingService fileSaving;
    private ConstraintLayout settingsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.fileSaving = new FileSavingService(this);

        settingsLayout = (ConstraintLayout) findViewById(R.id.settingsLayout);


        final RadioButton radioBlue = findViewById(R.id.colorBlue);
        radioBlue.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        //Change current theme to blue
                        //settingsLayout.setBackgroundColor(getResources().getColor(R.color.backgroundBlue));
                    }
                });


        final RadioButton radioRed = findViewById(R.id.colorRed);
        radioRed.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        //settingsLayout.setBackgroundColor(getResources().getColor(R.color.backgroundRed));
                    }
                });
    }
}
