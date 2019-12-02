package com.example.politicgame.Games.StampGame;

import android.util.Log;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

/** This class is in charge of interacting with TextView objects based on Proposals */
class TextViewManager {

  /** The proposal displayed when we run out of proposals to provide for the user */
  private Proposal emptyProposal;

  /** The current score of the user, it is being displayed by TextView */
  private int currentScore;

  /** The current Prompt being given to the user */
  private Proposal currentPrompt;

  /** The number of proposals left to be displayed */
  private int proposalsLeft;

  /** The list of Prompts (ie. Proposals) that has been given to the user */
  private List<Proposal> prompts;

  /** This is the iterator used to traverse through prompts */
  private Iterator<Proposal> iterator;

  TextViewManager(List<Proposal> prompts, Proposal emptyProposal) {
    this.prompts = prompts;
    this.emptyProposal = emptyProposal;
    this.currentScore = 50;
    this.iterator = prompts.iterator();
    this.proposalsLeft = prompts.size();

    updateCurrentPrompt();
    updateProposalLeft();
  }

  int getCurrentScore() {
    return currentScore;
  }

  Proposal getCurrentPrompt() {
    return currentPrompt;
  }

  int getProposalsLeft() {
    return proposalsLeft;
  }

  private void setCurrentScore(int newScore) {
    currentScore = newScore;
  }

  private void setProposalsLeft(int proposalsLeft) {
    this.proposalsLeft = proposalsLeft;
  }

  private void setCurrentPrompt(Proposal currentPrompt) {
    this.currentPrompt = currentPrompt;
  }

  /**
   * displays the current prompt onto the TextView object
   *
   * @param npcPrompt the TextView object that displays npcPrompt
   */
  void displayCurrentPrompt(TextView npcPrompt) {
    String npcProposal = this.getCurrentPrompt().getString();
    npcPrompt.setText(npcProposal);
  }

  /** Change the currentPrompt to the next prompty */
  void updateCurrentPrompt() {
    if (iterator.hasNext()) {
      setCurrentPrompt(iterator.next());
    } else {
      setCurrentPrompt(emptyProposal);
    }
  }

  /** Change the number of proposals */
  void updateProposalLeft() {
    if (getProposalsLeft() == 0) {
      setProposalsLeft(0);
    } else {
      setProposalsLeft(getProposalsLeft() - 1);
    }
  }

  void displayProposalLeft(TextView proposalNumDisplay) {
    String proposalLeft = ((Integer) this.getProposalsLeft()).toString();
    proposalNumDisplay.setText(proposalLeft);
  }

  void displayCurrentScore(TextView currentScoreDisplay) {
    String currentRating = ((Integer) this.getCurrentScore()).toString() + "%";
    currentScoreDisplay.setText(currentRating);
  }

  void updateCurrentScore(boolean clickedYes) {
    int minScore = 0;
    int maxScore = 100;
    int updatedScore;

    if (!clickedYes) {
      updatedScore = getCurrentScore() - getCurrentPrompt().getCategory();
    } else {
      updatedScore = getCurrentScore() + getCurrentPrompt().getCategory();
    }

    if (updatedScore < minScore) {
      setCurrentScore(minScore);
    } else if (updatedScore > maxScore) {
      setCurrentScore(maxScore);
    } else {
      setCurrentScore(updatedScore);
    }
  }

  void changeDisplay(
      TextView proposalLeftDisplay,
      TextView currentScoreDisplay,
      TextView npcPrompt) { // , Boolean clickedYes){
    //
    updateCurrentPrompt();
    updateProposalLeft();

    displayCurrentPrompt(npcPrompt);
    displayCurrentScore(currentScoreDisplay);
    displayProposalLeft(proposalLeftDisplay);
  }

  int checkGameWon() {
    if (getCurrentScore() == 100 || (getProposalsLeft() == 0 && getCurrentScore() >= 50)) {
      return 1;
    } else if (getCurrentScore() == 0 || (getProposalsLeft() == 0 && getCurrentScore() < 50)) {
      return 0;
    }
    return 2;
  }
}
