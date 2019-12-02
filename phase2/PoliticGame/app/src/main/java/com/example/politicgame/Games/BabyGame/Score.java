package com.example.politicgame.Games.BabyGame;

import android.graphics.Color;
import android.widget.TextView;

public class Score {

  private TextView scoreBox;

  /** This game's "score" is the baby's happiness */
  private int happiness;

  Score(TextView scoreBox, int initHappiness) {
    this.scoreBox = scoreBox;
    this.happiness = initHappiness;
  }

  /** Update score and returns new score */
  void updateScore(int happinessChange) {

    // Change color of text depending on score change
    if (happinessChange < -1) scoreBox.setTextColor(Color.parseColor("#ffcccc"));
    else if (happinessChange > 1) scoreBox.setTextColor(Color.parseColor("#b3ffb3"));
    else scoreBox.setTextColor(Color.parseColor("#ffffff"));

    // Check if bounds reached
    if (happiness + happinessChange > 100) {
      happiness = 100;
    } else if (happiness + happinessChange < 0) {
      happiness = 0;
    } else {
      happiness += happinessChange;
    }

    String scoreBoxText = "Happiness: " + happiness + "%";
    scoreBox.setText(scoreBoxText);
  }

  public int getHappiness() {
    return happiness;
  }
}
