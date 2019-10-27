package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.LeaderBoardActivity;
import com.example.politicgame.R;

public class PauseActivity extends AppCompatActivity {

    private final int DEFAULT_CODE = 0;
    private final int RESUME_CODE = 1;
    private final int QUIT_CODE = 2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        setTitle("Pause Menu");

        final Button resumeB = findViewById(R.id.resume);
        resumeB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        returnRequest(RESUME_CODE);
                    }
                });

        final Button quitB = findViewById(R.id.quit);
        quitB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        returnRequest(QUIT_CODE);
                    }
                });

    }


    private void returnRequest(int requestCode){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", requestCode);

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
