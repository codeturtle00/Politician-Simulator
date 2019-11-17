package com.example.politicgame.Games.BabyGame;

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

    if (happiness + happinessChange > 100) {
      happiness = 100;
    } else if (happiness + happinessChange < 0) {
      happiness = 0;
    } else {
      happiness += happinessChange;
    }

    // temp show score text. Will add a happiness meter gfx
    String scoreBoxText = "Happiness: " + happiness + "%";
    scoreBox.setText(scoreBoxText);
  }

  public int getHappiness() {
    return happiness;
  }
}
