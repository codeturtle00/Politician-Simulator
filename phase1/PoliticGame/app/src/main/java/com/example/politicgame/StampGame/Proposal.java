package com.example.politicgame.StampGame;

public class Proposal {
    private String prompt;
    private Verb action;
    private Noun item;
    private int category;

    public Proposal(String prompt, Verb action, Noun item){
        this.prompt = prompt;
        this.action = action;
        this.item = item;

        //The values of both getCategory functions will return -1 or 1 and the player will get the
        //result of category as their point if they answer with yes.
        this.category = this.action.getCategory() * this.item.getCategory();

        this.prompt = this.prompt + " " + this.action.getString() + " " + this.item.getString();
    }

    public String getString(){return prompt;}

    public int getCategory(){return category;}
}
