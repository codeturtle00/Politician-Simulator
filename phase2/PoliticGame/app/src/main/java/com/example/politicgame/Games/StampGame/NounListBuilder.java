package com.example.politicgame.Games.StampGame;

import java.util.List;

public class NounListBuilder extends AbstractWordListBuilder {

  private boolean countable;

  public void setCountable(boolean countable) {
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
