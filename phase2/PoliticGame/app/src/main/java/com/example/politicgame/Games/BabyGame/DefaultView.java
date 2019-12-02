package com.example.politicgame.Games.BabyGame;

/**
 * An interface designed to provide a dependency injection between BabyDefaultView and EventManager.
 * EventManager needs to access certain parts of BabyDefaultView but cannot do so directly because
 * BabyDefaultView depends on EventManager.
 */
interface DefaultView {
  /** Draws baby and events. */
  void drawUpdate();

  /**
   * Updates happiness.
   *
   * @param happinessChange the amount to change happiness by
   */
  void updateScore(int happinessChange);
}
