package com.example.politicgame.Games.StampGame;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWordListBuilder implements WordListBuilder {

  /** The list of Word this builder constructs */
  private List<Word> wordList = new ArrayList<>();

  /** Whether the list of word to be constructed contains positive or negative words */
  private boolean isPositive;

  /** The minimum and maximum score a word can obtain */
  private int min = 1;

  private int max = 5;

  /**
   * generate a double between(inclusive) double min and double max
   *
   * @param min the minimum double we can generate
   * @param max the maximum double we can generate
   * @return a double between(inclusive) min and max
   */
  double getRandomDoubleBetweenRange(double min, double max) {
    return (Math.random() * ((max - min) + 1)) + min;
  }

  List<Word> getWordList() {
    return wordList;
  }

  boolean isPositive() {
    return isPositive;
  }

  int getMin() {
    return min;
  }

  int getMax() {
    return max;
  }

  public void setIsPositive(boolean isPositive) {
    this.isPositive = isPositive;
  }

  public List<Word> getInstance() {
    return wordList;
  }
}
