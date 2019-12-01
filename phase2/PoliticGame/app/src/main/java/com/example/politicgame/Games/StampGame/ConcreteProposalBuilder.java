package com.example.politicgame.Games.StampGame;

/**
 * This is the class that implements the interface of ProposalBuilder, it is in charge of creating a
 * proposal.
 *
 * <p>This builder class will be instantiated in the prompt manager class inside its constructor;
 */
class ConcreteProposalBuilder implements ProposalBuilder {

  private String promptStart;

  private Proposal proposal;

  private Verb verb;

  private Noun noun;

  private boolean hasCountableNoun;

  /**
   * generate a double between(inclusive) double min and double max
   *
   * @param min the minimum double we can generate
   * @param max the maximum doulbe we can generate
   * @return a double between(inclusive) min and max
   */
  private double getRandomDoubleBetweenRange(double min, double max) {
    return (Math.random() * ((max - min) + 1)) + min;
  }

  public void setPromptStart(String start) {
    this.promptStart = start;
  }

  public void setVerb(Verb verb) {
    this.verb = verb;
  }

  public void setNoun(Noun noun) {
    this.noun = noun;
  }

  public void setHasCountableNoun(Boolean countable) {
    this.hasCountableNoun = countable;
  }

  /**
   * This is the last step of constructing a proposal, if the noun within the proposal is countable,
   * we will utilize the private method getRandomDoubleBetweenRange to generate a number for the
   * nouns. If the noun is not countable, then we use the other constructor for proposal.
   */
  public void setProposal() {
    if (hasCountableNoun) {
      int amount = (int) getRandomDoubleBetweenRange(1, 1000);
      this.proposal = new Proposal(this.promptStart, this.verb, this.noun, amount);
    } else {
      this.proposal = new Proposal(this.promptStart, this.verb, this.noun);
    }
  }

  public Proposal getInstance() {
    return this.proposal;
  }
}
