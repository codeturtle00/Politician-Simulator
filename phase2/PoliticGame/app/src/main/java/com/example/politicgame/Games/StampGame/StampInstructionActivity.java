package com.example.politicgame.Games.StampGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.GameActivity;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.GameMode.GameModeModel.GameMode;
import com.example.politicgame.R;

public class StampInstructionActivity extends GameActivity {
  private final String LEVEL_NAME = "LEVEL3";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    app = (PoliticGameApp) getApplication();

    if (((GameMode) getIntent().getSerializableExtra("GameMode")).isGameComplete(app)) {
      openSummary();
    }

    System.out.println("The current theme is blue: " + app.isThemeBlue());

    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stamp_instruction);

    setTitle("The Stamp Game Instructions");

    final Button button = findViewById(R.id.start_game);
    button.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            startStampGame();
          }
        });
  }

  public void startStampGame() {
    Intent startStampGame = new Intent(this, StampActivity.class);
    startStampGame.putExtra("GameMode", getIntent().getSerializableExtra("GameMode"));
    startActivity(startStampGame);
    finish();
  }
}
