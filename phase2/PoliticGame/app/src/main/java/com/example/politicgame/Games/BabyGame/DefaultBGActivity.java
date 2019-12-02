package com.example.politicgame.Games.BabyGame;

/**
 * An interface designed to provide a dependency injection between BabyActivity and BabyDefaultView.
 * BabyDefaultView needs to access certain parts of BabyActivity but cannot do so directly because
 * BabyActivity depends on BabyDefaultView.
 */
interface DefaultBGActivity {

  /**
   * Updates score in BabyActivity
   *
   * @param happinessChange the amount to change happiness by
   */
  void updateScore(int happinessChange);

  /**
   * Updates the remaining time.
   *
   * @param time the remaining time
   * @param outOfTime whether the time is up or not
   */
  void updateTime(String time, boolean outOfTime);
}
