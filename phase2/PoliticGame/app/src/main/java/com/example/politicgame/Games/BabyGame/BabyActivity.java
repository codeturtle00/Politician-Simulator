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
import com.example.politicgame.GameMode.GameMode;
import com.example.politicgame.Games.SpeechGame.SpeechInstructionActivity;
import com.example.politicgame.Pausing.PauseButton;
import com.example.politicgame.R;

public class BabyActivity extends GameActivity implements BabyDraw {
  /** This game's level. */
  private final String LEVEL_NAME = "LEVEL1";

  /** The TextView used to display the action to be performed. */
  private TextView eventActionText;

  /** The TextView used to display the remaining time. */
  private TextView timerDisplay;

  private BabyView babyView;

  private Score score;

  private int happiness;

  /** The game's timer. */
  private Timer timer;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Embed BabyView into xml layout
    setContentView(R.layout.activity_baby);
    babyView = new BabyView(this);
    babyView.setBabyDraw(this);

    FrameLayout babyFrame = findViewById(R.id.babyFrame);
    babyFrame.addView(babyView);

    setTitle("The Baby Game");

    // Initialize Score
    score = new Score((TextView) findViewById(R.id.scoreDisplay), 50);

    // Event Action
    eventActionText = findViewById(R.id.eventActionText);
    eventActionText.setText("test");

    // Timer View
    timerDisplay = findViewById(R.id.timerDisplay);
    timer = new Timer(this);

    // Generate Pause Button
    new PauseButton((ConstraintLayout) findViewById(R.id.babyLayout), this);

    // TODO: DELETE WHEN GAME IS FINISHED
    Button next = findViewById(R.id.nextGame);
    next.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            openSpeechGame();
          }
        });
  }

  /** Pauses game. */
  @Override
  protected void onPause() {
    super.onPause();
    timer.pause();
    babyView.pause();
  }

  /** Resumes game. */
  @Override
  protected void onResume() {
    super.onResume();
    timer.resume();
    babyView.resume();
  }

  /** Opens the next level. */
  void openSpeechGame() {
    GameMode gm = (GameMode) getIntent().getSerializableExtra("GameMode");

    //These two lines replace the two below
    gm.save(app, happiness);
    Intent switchSpeechIntent = gm.next(this);

    //Intent switchSpeechIntent = new Intent(this, SpeechInstructionActivity.class);
    //saveGame(happiness, LEVEL_NAME);

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
    if (happiness == 0) {
      gameOver();
    } else if (happiness == 100) {
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
  public void updateEventAction(final String eventAction) {
    System.out.println("we hav arrived");
    runOnUiThread(
        new Runnable() {

          @Override
          public void run() {
            eventActionText.setText(eventAction);
          }
        });
    System.out.println("Event action set!");
  }
}
