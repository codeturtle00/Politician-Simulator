package com.example.politicgame.Games.BabyGame;

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
import com.example.politicgame.GameMode.GameModeModel.GameMode;
import com.example.politicgame.Pausing.PauseButton;
import com.example.politicgame.R;

public class BabyActivity extends GameActivity implements DefaultBGActivity {

  /** This game's level. */
  private final String LEVEL_NAME = "LEVEL1";

  private final int INIT_HAPPINESS = 50;
  private static final int INIT_MILLISEC = 60000;

  /** The TextView used to display the remaining time. */
  private TextView timerDisplay;

  private BabyDefaultView babyView;

  private Score score;

  private int happiness;

  /** The game's babyGameTimer. */
  private BabyGameTimer babyGameTimer;

  private boolean gameFinished;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("The Baby Game");
    setContentView(R.layout.activity_baby);
    timerDisplay = findViewById(R.id.timerDisplay);
    gameFinished = false;
    babyView = new BabyDefaultView(this);
    babyView.setDefaultBGActivity(this);

    // Embed DefaultView into xml layout
    FrameLayout babyFrame = findViewById(R.id.babyFrame);
    babyFrame.addView(babyView);

    // Initialize Score
    score = new Score((TextView) findViewById(R.id.scoreDisplay), INIT_HAPPINESS);

    // BabyGameTimer View
    babyGameTimer = new BabyGameTimer(this, INIT_MILLISEC);

    // Generate Pause Button
    new PauseButton((ConstraintLayout) findViewById(R.id.babyLayout), this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    babyGameTimer.pause();
    babyView.pause();
  }

  @Override
  protected void onResume() {
    super.onResume();
    babyGameTimer.resume();
    babyView.resume();
  }

  /** Saves stats and opens the next level. */
  void openSpeechGame() {
    GameMode gm = (GameMode) getIntent().getSerializableExtra("GameMode");
    gm.save(app, happiness);
    Intent switchSpeechIntent = gm.next(this);
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
            openMainMenu();
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
    score.updateScore(happinessChange);
    happiness = score.getHappiness();
    babyView.setBabyMood(happiness);

    if (!gameFinished) {
      if (happiness == 0) {
        gameFinished = true;
        gameOver();
      } else if (happiness == 100) {
        gameFinished = true;
        gameOutro();
      }
    }
  }

  /**
   * Updates the time displayed after each babyGameTimer tick.
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
}
