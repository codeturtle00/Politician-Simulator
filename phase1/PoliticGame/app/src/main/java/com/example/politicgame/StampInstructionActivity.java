package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StampInstructionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_instruction);

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
        Intent switchStampIntent = new Intent(this, StampActivity.class);
        startActivity(switchStampIntent);
    }
}
