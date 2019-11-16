package com.example.politicgame.GamesActivity.BabyGame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.politicgame.GameActivity;
import com.example.politicgame.PauseButton;
import com.example.politicgame.R;
import com.example.politicgame.GamesActivity.SpeechGame.SpeechInstructionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BabyActivity extends GameActivity implements BabyDraw {
  /** This game's level. */
  private final String LEVEL_NAME = "LEVEL1";

  /** Happiness of the baby. Also the player's score. */
  private Integer happiness = 50;

  /** The TextView used to display the score. */
  private TextView scoreDisplay;

  /** The TextView used to display the action to be performed. */
  private TextView eventActionText;

  /** The TextView used to display the remaining time. */
  private TextView timerDisplay;

  /** The game's timer. */
  private Timer timer;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Embed BabyView into xml layout
    setContentView(R.layout.activity_baby);
    BabyView babyView = new BabyView(this);
    babyView.setBabyDraw(this);

    FrameLayout babyFrame = findViewById(R.id.babyFrame);
    babyFrame.addView(babyView);

    setTitle("The Baby Game");

    // Score
    scoreDisplay = findViewById(R.id.scoreDisplay);
    String score = happiness.toString() + "%";
    scoreDisplay.setText(score);

    // Event Action
    eventActionText = findViewById(R.id.eventActionText);

    // Timer View
    timerDisplay = findViewById(R.id.timerDisplay);
    timer = new Timer(this, babyView);

    // Next Button (delete later)
//    final Button next = findViewById(R.id.next);
//    next.setOnClickListener(
//        new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            openSpeechGame();
//          }
//        });

    // Generate Pause Button
    new PauseButton((ConstraintLayout) findViewById(R.id.babyLayout), this);
  }

  /** Pauses game. */
  @Override
  protected void onPause() {
    super.onPause();
    timer.pause();
  }

  /** Resumes game. */
  @Override
  protected void onResume() {
    super.onResume();
    timer.resume();
  }

  /** Opens the next level. */
  void openSpeechGame() {
    Intent switchSpeechIntent = new Intent(this, SpeechInstructionActivity.class);
    saveGame(happiness, LEVEL_NAME);
    startActivity(switchSpeechIntent);
    finish();
  }

  /** Displays a screen telling the player if they won or lost the game. */
  public void gameOver() {
    onPause();
    final Dialog gameOverDialog = new Dialog(this);
    gameOverDialog.setContentView(R.layout.game_over);
    gameOverDialog.setCancelable(false);
    gameOverDialog.setCanceledOnTouchOutside(false);
    gameOverDialog
        .getWindow()
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    Button quitB = gameOverDialog.findViewById(R.id.goBack);
    quitB.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            gameOverDialog.dismiss();
            openLoggedIn();
          }
        });
    gameOverDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    gameOverDialog.show();
  }

  /** Displays a screen showing telling the user their score after time runs out. */
  public void gameOutro() {
    onPause();
    final Dialog gameOutroDialog = new Dialog(this);
    gameOutroDialog.setContentView(R.layout.baby_outro);
    gameOutroDialog.setCancelable(false);
    gameOutroDialog.setCanceledOnTouchOutside(false);
    gameOutroDialog
        .getWindow()
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    TextView score = gameOutroDialog.findViewById(R.id.score);
    score.setText(String.format("Your score is %d", happiness));
    ImageButton nextButton = gameOutroDialog.findViewById(R.id.next);

    nextButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            gameOutroDialog.dismiss();
            openSpeechGame();
          }
        });
    gameOutroDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    gameOutroDialog.show();
  }

  /**
   * Changes score by happinessChange.
   *
   * @param happinessChange the amount to change happiness by
   */
  @Override
  public void updateScore(int happinessChange) {
    if (happiness + happinessChange > 100) {
      happiness = 100;
    } else if (happiness + happinessChange < 0) {
      happiness = 0;
    } else {
      happiness += happinessChange;
    }
    String score = "Score: " + happiness.toString() + "%";
    scoreDisplay.setText(score);
    if (happiness == 0) {
      gameOver();
    }
    if (happiness == 100) {
      gameOutro();
    }
  }

  /**
   * Updates the time displayed after each timer tick.
   *
   * @param time the time remaining in the game
   * @param outOfTime whether the time is up or not
   */
  @Override
  public void updateTime(String time, boolean outOfTime) {
    if (outOfTime) gameOutro();
    else {
      String timeLeft = "Time remaining: " + time;
      timerDisplay.setText(timeLeft);
      updateScore(-1);
    }
  }

  /**
   * Tells user what action to perform.
   *
   * @param eventAction the action to perform
   */
  public void updateEventAction(String eventAction) {
    eventActionText.setText(eventAction);
    System.out.println("Event action set!");
  }
}
