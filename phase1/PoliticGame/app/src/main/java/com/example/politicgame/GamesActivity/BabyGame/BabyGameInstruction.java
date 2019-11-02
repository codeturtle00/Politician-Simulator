package com.example.politicgame.GamesActivity.BabyGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.GameActivity;
import com.example.politicgame.R;

public class BabyGameInstruction extends GameActivity {

  public void onCreate(Bundle savedInstanceState) {
    System.out.println("The current theme is blue: " + app.isThemeBlue());

    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }

    super.onCreate(savedInstanceState);
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
    startActivity(startBabyGame);
    finish();
  }
}
