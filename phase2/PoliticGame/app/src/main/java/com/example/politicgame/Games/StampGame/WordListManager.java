package com.example.politicgame.Games.StampGame;

import java.util.ArrayList;
import java.util.List;

/** This is the class that contains different list of */
class WordListManager {

  private List<Word> Verbs = new ArrayList<>();
  private List<Word> Nouns = new ArrayList<>();

  private StringListManager stringListManager;

  WordListManager(StringListManager stringListManager) {
    this.stringListManager = stringListManager;

    constructNouns();
    constructVerbs();
  }

  List<Word> getVerbs() {
    return Verbs;
  }

  List<Word> getNouns() {
    return Nouns;
  }

  /**
   * This method utilizes the VerbListBuilder class to construct List of Verbs. Once the
   * corresponding verbList has been constructed, the Verbs list adds the element of that verb list
   *
   * @param isPositive state if the verb list to be constructed is positive or negative
   */
  private void constructVerbList(boolean isPositive) {

    VerbListBuilder verbListBuilder = new VerbListBuilder();

    verbListBuilder.setIsPositive(isPositive);

    if (isPositive) {
      verbListBuilder.setList(stringListManager.getPosVerbStringList());
    } else {
      verbListBuilder.setList(stringListManager.getNegVerbStringList());
    }

    getVerbs().addAll(verbListBuilder.getInstance());
  }

  /**
   * This method utilizes the NounListBuilder class to construct List of Nouns. Once the
   * corresponding nounList has been constructed, the Nouns list adds the elements of that noun list
   *
   * @param isPositive state if the noun list to be constructed contains all positive or negative
   * @param countable state if the noun list to be constructed contains all countable nouns
   */
  private void constructNounList(boolean isPositive, boolean countable) {

    NounListBuilder nounListBuilder = new NounListBuilder();

    nounListBuilder.setIsPositive(isPositive);
    nounListBuilder.setCountable(countable);

    if (isPositive) {
      if (countable) {
        nounListBuilder.setList(stringListManager.getPosCNounStringList());
      } else {
        nounListBuilder.setList(stringListManager.getPosNotCNounStringList());
      }
    } else {
      if (countable) {
        nounListBuilder.setList(stringListManager.getNegCNounStringList());
      } else {
        nounListBuilder.setList(stringListManager.getNegNotCNounStringList());
      }
    }

    getNouns().addAll(nounListBuilder.getInstance());
  }

  /** construct the ArrayList nouns using method constructNounList(). */
  private void constructNouns() {
    constructVerbList(false);
    constructVerbList(true);
  }

  /** construct the ArrayList verbs using method constructVerbList(). */
  private void constructVerbs() {
    constructNounList(true, true);
    constructNounList(true, false);
    constructNounList(false, true);
    constructNounList(false, false);
  }
}
