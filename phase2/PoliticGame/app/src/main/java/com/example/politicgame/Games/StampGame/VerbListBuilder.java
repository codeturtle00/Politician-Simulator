package com.example.politicgame.Games.StampGame;

import java.util.List;

public class VerbListBuilder extends AbstractWordListBuilder {

  @Override
  public void setList(List<String> stringList) {
    for (String word : stringList) {
      int score = (int) getRandomDoubleBetweenRange(getMin(), getMax());
      if (!isPositive()) score = -score;

      getWordList().add(new Verb(word, score));
    }
  }
}
