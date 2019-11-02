package com.example.politicgame.GamesActivity.StampGame;

/**
 * A Word.
 *
 * <p>This is the superclass for Noun and Verb
 */
class Word {
  /** The String value of the Word */
  private String value;

  /**
   * The category of the Word, by this, we mean the point that this word gives when we construct a
   * proposal using this word
   */
  private int category;

  Word(String value, int category) {
    this.value = value;
    this.category = category;
  }

  public String getString() {
    return this.value;
  }

  int getCategory() {
    return this.category;
  }
}
