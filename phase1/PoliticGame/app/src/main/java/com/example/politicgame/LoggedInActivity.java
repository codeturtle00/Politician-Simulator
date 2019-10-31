package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoggedInActivity extends GameActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
    }
}
