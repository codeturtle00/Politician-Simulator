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
                "send aid to",
                "donate money to charities that work with"));

        for (String item: verbListPositive){
            verbs.add(new Verb(item, 1));
        }


        //Positive verbs that has a negative effect on the object
        List<String> verbListNegative = new ArrayList<String>(Arrays.asList(
                "send a police squad to detain and imprison",
                "punch",
                "launch nukes against",
                "send an army against"));

        for (String item: verbListNegative){
            verbs.add(new Verb(item, -1));
        }


        //Positive nouns that are not amountable
        List<String> nounListPositiveNA = new ArrayList<String>(Arrays.asList(
                "Canada",
                "Gandhi, a recently popular peace advocate who campaigns in India",
                "the popular late-night TV show host John Olive Oil",
                "Bill Rye, a once popular figure in science who recently published a paper on the benefits of foot rubs",
                "Toe-Knee, a rising star in the genre of Jazz, who recently released his hit album \"CS Blues\"",
                "Jacki, a citizen who has been wrongfully imprisoned in the country of Canada for not holding the door for the boy behind him"));

        for (String item: nounListPositiveNA){
            nouns.add(new Noun(item, 1, false));
        }


        //Positive nouns that are amountable
        List<String> nounListPositiveYA = new ArrayList<String>(Arrays.asList(
                "children",
                "puppies",
                "Boundless Peacocks, the very last of their kind"));

        for (String item: nounListPositiveYA){
            nouns.add(new Noun(item, 1, true));
        }


        //Negative nouns that are not amountable
        List<String> nounListNegativeNA = new ArrayList<String>(Arrays.asList(
                "the country of North Korea",
                "Colin, a medical practitioner found to have cheated on his medical exams after a related illegal nose smuggling ring was busted",
                "Kavin, a phantom thief who masterminded the theft all the laptop chargers, but not the laptops, at the University of Toronto"));

        for (String item: nounListNegativeNA){
            nouns.add(new Noun(item, -1, false));
        }


        //Negative nouns that are amountable
        List<String> nounListNegativeYA = new ArrayList<String>(Arrays.asList(
                "seal clubbers"));

        for (String item: nounListNegativeYA){
            nouns.add(new Noun(item, -1, true));
        }
    }

    //Must be called first
    public void createPrompt(){
        int verbIndex = (int)(Math.random() * (verbs.size()));
        int nounIndex = (int)(Math.random() * (nouns.size()));
        String promptBegin = "Hello,";

        if (nouns.get(nounIndex).getAmountable()){
            int amount = (int)(Math.random() * (1000));
            currentPrompt = new Proposal(promptBegin, verbs.remove(verbIndex), nouns.remove(nounIndex), amount);
        }

        else{
            currentPrompt = new Proposal(promptBegin, verbs.remove(verbIndex), nouns.remove(nounIndex));
        }

        prompts.add(currentPrompt);
    }

    public Proposal getCurrentPrompt(){return currentPrompt;}
}
