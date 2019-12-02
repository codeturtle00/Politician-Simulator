package com.example.politicgame.Games.StampGame;

import java.util.List;

public class NounListBuilder extends AbstractWordListBuilder {

  /** This shows whether the builder is building a list of countable nouns*/
  private boolean countable;

  void setCountable(boolean countable) {
    this.countable = countable;
  }

  public void setList(List<String> stringList) {
    for (String word : stringList) {
      int score = (int) getRandomDoubleBetweenRange(getMin(), getMax());
      if (!isPositive()) score = -score;

      getWordList().add(new Noun(word, score, countable));
    }
  }
}
