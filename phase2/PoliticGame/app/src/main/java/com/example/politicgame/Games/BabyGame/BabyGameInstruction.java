package com.example.politicgame.Games.BabyGame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.GameActivity;
import com.example.politicgame.GameMode.GameMode;
import com.example.politicgame.GameMode.SpeechArcade;
import com.example.politicgame.GameMode.StampArcade;
import com.example.politicgame.Games.SpeechGame.SpeechInstructionActivity;
import com.example.politicgame.R;

public class BabyGameInstruction extends GameActivity {
  /** This game's level. */
  private final String LEVEL_NAME = "LEVEL1";

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (((GameMode)getIntent().getSerializableExtra("GameMode")).isGameComplete(app)) {
      Log.i("BabyGame", "BabyGame is already complete, move on");
      openSpeechGame();
    }

    setContentView(R.layout.activity_baby_instruction);
    setTitle("The Baby Game Instructions");

    // Start game button
    final Button button = findViewById(R.id.start_game);
    button.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            startBabyGame();
          }
        });
  }

  /** Starts the baby game */
  void startBabyGame() {
    Intent startBabyGame = new Intent(this, BabyActivity.class);
    startBabyGame.putExtra("GameMode",getIntent().getSerializableExtra("GameMode"));
    startActivity(startBabyGame);
    finish();
  }

  /** Opens the next level */
  void openSpeechGame() {
    Intent switchSpeechIntent = new Intent(this, SpeechInstructionActivity.class);
    switchSpeechIntent.putExtra("GameMode", new SpeechArcade(/*app*/));
    startActivity(switchSpeechIntent);
    finish();
  }
}
