package com.example.politicgame.Games.BabyGame;

/**
 * An interface designed to provide a dependency injection between BabyActivity and BabyView.
 * BabyView needs to access certain parts of BabyActivity but cannot do so directly because
 * BabyActivity depends on BabyView.
 */
interface BabyDraw {

  /**
   * Updates score in BabyActivity
   *
   * @param happinessChange the amount to change happiness by
   */
  void updateScore(int happinessChange);

  /**
   * Updates event action in BabyActivity.
   *
   * @param eventAction the new event to perform
   */
  void updateEventAction(String eventAction);

  /**
   * Updates the remaining time.
   *
   * @param time the remaining time
   * @param outOfTime whether the time is up or not
   */
  void updateTime(String time, boolean outOfTime);
}
