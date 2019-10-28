package com.example.politicgame.BabyGame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

import com.example.politicgame.MainActivity;
import com.example.politicgame.PauseActivity;
import com.example.politicgame.R;
import com.example.politicgame.SpeechGame.SpeechInstructionActivity;

public class BabyActivity extends AppCompatActivity {
  static Integer happiness = 50;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_baby);
    final TextView timer = findViewById(R.id.timer);
    final TextView score_display = findViewById(R.id.score_display);

    final Button pauseB = findViewById(R.id.pause);
    pauseB.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            Log.i("Button", "The pause button has been clicked");
            // The method below will pause the game and handle the following inputs
            openPauseMenu();
          }
        });

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
    Intent switchSpeechIntent = new Intent(this, SpeechInstructionActivity.class);
    startActivity(switchSpeechIntent);
  }

  public void openPauseMenu() {
    Intent pauseMenuIntent = new Intent(this, PauseActivity.class);
    startActivityForResult(pauseMenuIntent, 1);
  }

  public void openMainMenu() {
    Intent mainMenuIntent = new Intent(this, MainActivity.class);
    startActivity(mainMenuIntent);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // requestCode refers to the request code parameter of openPauseMenu's startActivityForResult
    // call
    if (requestCode == 1) {
      if (resultCode == RESULT_OK) {
        int userInput = data.getIntExtra("result", 0);

        if (userInput == 1) {
          Log.i("Pause Result", "User has decided to resume play");
        } else if (userInput == 2) {
          Log.i("Pause Result", "User has decided to quit the game");
          openMainMenu();
        }
      } else {
        Log.i("Result Code", "Result code is " + resultCode);
      }
    }
  }
}
