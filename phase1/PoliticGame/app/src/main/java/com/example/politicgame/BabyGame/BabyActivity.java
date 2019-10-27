package com.example.politicgame.BabyGame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import java.util.Random;

import com.example.politicgame.R;
import com.example.politicgame.SpeechActivity;

public class BabyActivity extends AppCompatActivity {
  static Integer happiness = 50;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_baby);
    final TextView timer = findViewById(R.id.timer);
    final TextView score_display = findViewById(R.id.score_display);

    String score = score_display.toString() + "%";
      score_display.setText(score);

    new CountDownTimer(60000, 1000) {

      public void onTick(long millisUntilFinished) {
        String timeLeft = "seconds remaining: " + millisUntilFinished / 1000;
        timer.setText(timeLeft);
        if ((millisUntilFinished / 1000) % 5 == 0) {
          randomEvent();
        }
      }

      public void onFinish() {
        openSpeechGame();
      }
    }.start();
  }

  void randomEvent() {
    Random rand = new Random();
    final int randomNum = rand.nextInt((4 - 0) + 1) + 1;
    if (randomNum == 1) {
      startActivityForResult(new Intent(this, Shake.class), randomNum);
    }

    new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {}

        @Override
        public void onFinish() {
            finishActivity(randomNum);
        }
    }.start();
  }

  void openSpeechGame() {
    Intent switchSpeechIntent = new Intent(this, SpeechActivity.class);
    startActivity(switchSpeechIntent);
  }
}
