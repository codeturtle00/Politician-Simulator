package com.example.politicgame.StampGame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StampGameHandler {
    private List<Verb> verbs;
    private List<Noun> nouns;
    private List<Proposal> prompts;
    private Proposal currentPrompt;

    public StampGameHandler(){
        verbs = new ArrayList<Verb>();
        nouns = new ArrayList<Noun>();
        prompts = new ArrayList<Proposal>();

        //Positive verbs that has a positive effect on the object
        List<String> verbListPositive = new ArrayList<String>(Arrays.asList(
                "save",
                "send aid",
                "donate money to",
                "legalize"));

        for (String item: verbListPositive){
            verbs.add(new Verb(item, 1));
        }


        //Positive verbs that has a negative effect on the object
        List<String> verbListNegative = new ArrayList<String>(Arrays.asList(
                "imprison",
                "punch",
                "launch nukes against",
                "send an army against"));

        for (String item: verbListNegative){
            verbs.add(new Verb(item, -1));
        }


        //Positive nouns that are not amountable
        List<String> nounListPositiveNA = new ArrayList<String>(Arrays.asList(
                "Canada",
                "Gandhi, a recently popular peace advocate who campaigns in India"));

        for (String item: nounListPositiveNA){
            nouns.add(new Noun(item, 1, false));
        }


        //Positive nouns that are amountable
        List<String> nounListPositiveYA = new ArrayList<String>(Arrays.asList(
                "children",
                "puppies"));

        for (String item: nounListPositiveYA){
            nouns.add(new Noun(item, 1, true));
        }


        //Negative nouns that are not amountable
        List<String> nounListNegativeNA = new ArrayList<String>(Arrays.asList(
                "The country of North Korea"));

        for (String item: nounListNegativeNA){
            nouns.add(new Noun(item, -1, false));
        }


        //Negative nouns that are not amountable
        List<String> nounListNegativeYA = new ArrayList<String>(Arrays.asList(
                "seal clubbers"));

        for (String item: nounListNegativeYA){
            nouns.add(new Noun(item, -1, false));
        }
    }

    //Must be called first
    public void createPrompt(){
        int verbIndex = (int)(Math.random() * (verbs.size()));
        int nounIndex = (int)(Math.random() * (nouns.size()));

        currentPrompt = new Proposal("This is an example prompt, but I want you to ", verbs.remove(verbIndex), nouns.remove(nounIndex));

        prompts.add(currentPrompt);
    }

    public Proposal getCurrentPrompt(){return currentPrompt;}
}
