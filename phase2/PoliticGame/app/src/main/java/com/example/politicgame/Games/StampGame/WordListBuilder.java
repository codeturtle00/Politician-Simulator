package com.example.politicgame.Games.StampGame;

import java.util.List;

/** This is the interface for constructing list that contains Words. */
public interface WordListBuilder {

  public void setList(List<String> stringList);

  public void setIsPositive(boolean isPositive);

  public List<Word> getInstance();
}
