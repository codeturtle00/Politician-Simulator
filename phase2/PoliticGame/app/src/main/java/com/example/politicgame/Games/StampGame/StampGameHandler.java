package com.example.politicgame.Games.StampGame;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Game handler for Stamp Game. It is in charge of updating the score and storing the score for
 * the stamp game that is going on
 */
class StampGameHandler {

  private WordListManager wordListManager;

  private StringListManager stringListManager;

  private ProposalsManager proposalsManager;

  private TextViewManager textViewManager;

  /**
   * The constructor for the game handler
   *
   * @param context The context of the handler, this is passed in from StampActivity, and is used to
   *     instantiate StringListManager;
   */
  StampGameHandler(Context context) {
    this.stringListManager = new StringListManager(context);
    this.wordListManager = new WordListManager(this.stringListManager);

    List<Word> verbs = wordListManager.getVerbs();
    List<Word> nouns = wordListManager.getNouns();
    List<String> promptBeginList = stringListManager.getPromptStartList();
    proposalsManager = new ProposalsManager(nouns, verbs, promptBeginList);

    List<Proposal> prompts = proposalsManager.generateProposalList(10);
    this.textViewManager = new TextViewManager(prompts, proposalsManager.generateEmptyProposal());
  }

  /** This changes the display of the Activity when yes or no button is pressed */
  void changeDisplay(
      TextView proposalLeftDisplay, TextView currentScoreDisplay, TextView npcPrompt) {
    this.textViewManager.changeDisplay(proposalLeftDisplay, currentScoreDisplay, npcPrompt);
  }

  /** The three methods below are used to display the initial values when the game starts */
  void displayCurrentPrompt(TextView tv) {
    this.textViewManager.displayCurrentPrompt(tv);
  }

  void displayProposalLeft(TextView tv) {
    this.textViewManager.displayProposalLeft(tv);
  }

  void displayCurrentScore(TextView tv) {
    this.textViewManager.displayCurrentScore(tv);
  }

  /** Return the currentScore of the user, this is used to record score on leaderboard */
  int getCurrentScore() {
    return this.textViewManager.getCurrentScore();
  }

  int checkGameWon() {
    return this.textViewManager.checkGameWon();
  }

  void updateCurrentScore(boolean clickedYes) {
    this.textViewManager.updateCurrentScore(clickedYes);
  }
}
