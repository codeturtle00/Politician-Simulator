package com.example.politicgame.Games.StampGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.GameActivity;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.GameMode.GameModeModel.GameMode;
import com.example.politicgame.R;

public class StampActivityLost extends GameActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    app = (PoliticGameApp) getApplication();

    System.out.println("The current theme is blue: " + app.isThemeBlue());

    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stamp_lost);

    setTitle("Oh no...");

    final Button button = findViewById(R.id.stamp_game_lost_leaderboard);
    button.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            openFromStamp();
          }
        });
  }

  public void openFromStamp() {
    GameMode gm = (GameMode) getIntent().getSerializableExtra("GameMode");

    gm.save(app, getIntent().getIntExtra("score", 0));
    Intent summaryIntent = gm.next(this);

    startActivity(summaryIntent);
    finish();
  }
}
