package com.example.politicgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        final Button button = findViewById(R.id.back);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        BackToMenu();
                    }
                });
    }

    public void BackToMenu() {
        Intent switchBabyIntent = new Intent(this, MainActivity.class);
        startActivity(switchBabyIntent);
    }
}