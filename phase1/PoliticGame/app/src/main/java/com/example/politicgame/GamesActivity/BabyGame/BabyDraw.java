package com.example.politicgame.GamesActivity.BabyGame;

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

  void updateEventAction(String eventAction);

  void updateTime(String time, boolean b);
}
