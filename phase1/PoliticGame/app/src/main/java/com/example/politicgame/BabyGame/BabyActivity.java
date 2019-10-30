package com.example.politicgame.BabyGame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.politicgame.MainActivity;
import com.example.politicgame.PauseButton;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.SpeechGame.SpeechInstructionActivity;

public class BabyActivity extends AppCompatActivity {
  // Happiness of the baby. Also the player's score.
  protected PoliticGameApp app;
  static Integer happiness = 50;

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

    // Embed BabyView into xml layout
    setContentView(R.layout.activity_baby);
    BabyView babyView = new BabyView(this);
    FrameLayout babyFrame = findViewById(R.id.babyFrame);
    babyFrame.addView(babyView);

    setTitle("The Baby Game");

    // Timer
    final TextView timer_display = findViewById(R.id.timer_display);
    String timeLeft = babyView.getTimeLeft().toString() + "%";
    timer_display.setText(timeLeft);

    // Score
    TextView score_display = findViewById(R.id.score_display);
    String score = happiness.toString() + "%";
    score_display.setText(score);

    // Next Button (delete later)
    final Button next = findViewById(R.id.next);
    next.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            openSpeechGame();
          }
        });

    // Generate Pause Button
    new PauseButton((ConstraintLayout) findViewById(R.id.babyLayout), this);
  }

  void openSpeechGame() {
    Intent switchSpeechIntent = new Intent(this, SpeechInstructionActivity.class);
    startActivity(switchSpeechIntent);
    finish();
  }

  public void openMainMenu() {
    Intent mainMenuIntent = new Intent(this, MainActivity.class);
    startActivity(mainMenuIntent);
    finish();
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
