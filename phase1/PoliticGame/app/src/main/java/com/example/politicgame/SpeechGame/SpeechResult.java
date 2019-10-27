package com.example.politicgame.SpeechGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.R;

public class SpeechResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_result);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(SpeechActivity.INPUT_MESSAGE);

        // Capture the layout's TextView and set the string as its text
         TextView textView = findViewById(R.id.userInput);
         textView.setText(message);

        final Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        returnSpeech();
                    }
                });

    }

    public void returnSpeech(){
        Intent backToSpeech = new Intent(this, SpeechActivity.class);
        startActivity(backToSpeech);
    }


}
