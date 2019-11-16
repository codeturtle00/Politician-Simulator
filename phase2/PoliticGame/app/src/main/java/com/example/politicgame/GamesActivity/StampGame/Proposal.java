package com.example.politicgame.GamesActivity.StampGame;

/**
 * A Proposal consisting of Verb and Noun
 *
 * <p>The proposal will either have a negative category(score) or positive one. We multiply the
 * category of the verb and noun in the proposal together to get the score because a negative action
 * on a positive thing is overall negative (vise versa), and when we do positive actions on a
 * positive noun the proposal is still positive (vise versa).
 */
public class Proposal {
  /** The String value of the Prompt, which consists of the value of Verb and Noun */
  private String prompt;

  /** The verb in this Proposal */
  private Verb action;

  /** The Noun in this Proposal */
  private Noun item;

  /**
   * The category of this proposal, by this we mean the points we will get if the politician were to
   * accept this proposal. This is the value of Verb.category * Noun.category
   */
  private int category;

  Proposal(String prompt, Verb action, Noun item) {
    this.prompt = prompt;
    this.action = action;
    this.item = item;

    // The values of both getCategory functions will return -1 or 1 and the player will get the
    // result of category as their point if they answer with yes or no.
    this.category = this.action.getCategory() * this.item.getCategory();

    this.prompt = this.prompt + " " + this.action.getString() + " " + this.item.getString();
  }

  Proposal(String prompt, Verb action, Noun item, int amount) {
    this.prompt = prompt;
    this.action = action;
    this.item = item;

    // The values of both getCategory functions will return -1 or 1 and the player will get the
    // result of category as their point if they answer with yes or no.
    this.category = this.action.getCategory() * this.item.getCategory();

    this.prompt =
        this.prompt
            + " "
            + this.action.getString()
            + " the "
            + amount
            + " "
            + this.item.getString();
  }

  public String getString() {
    return prompt;
  }

  int getCategory() {
    return category;
  }
}
