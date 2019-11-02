package com.example.politicgame.GamesActivity.StampGame;

class Noun extends Word {
  private boolean amountable;

  Noun(String value, int category, boolean amountable) {
    super(value, category);
    this.amountable = amountable;
  }

  boolean getAmountable() {
    return this.amountable;
  }
}
