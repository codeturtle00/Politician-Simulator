package com.example.politicgame.GamesActivity.StampGame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.GameActivity;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;

public class StampActivityWon extends GameActivity {

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
    setContentView(R.layout.activity_stamp_won);

    setTitle("Good job!");

    final Button button = findViewById(R.id.stamp_game_won_leaderboard);
    button.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            openSummary();
          }
        });
  }
}
