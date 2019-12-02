package com.example.politicgame.Games.StampGame;

interface ProposalBuilder {

  public void setPromptStart(String start);

  public void setVerb(Verb verb);

  public void setNoun(Noun noun);

  public void setProposal();

  public Proposal getInstance();
}
