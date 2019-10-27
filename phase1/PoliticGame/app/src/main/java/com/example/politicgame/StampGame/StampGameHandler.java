package com.example.politicgame.StampGame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* TODO: fix the index out of bound bug
*  TODO: fix the over rated system
*   TODO: Optimize the scoring system
* */
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
                "campaign with the best interests of",
                "send aid to",
                "donate money to charities that work with",
                "gift a bouquet of flowers to"));

        for (String item: verbListPositive){
            verbs.add(new Verb(item, 1));
        }


        //Positive verbs that has a negative effect on the object
        List<String> verbListNegative = new ArrayList<String>(Arrays.asList(
                "send a police squad to detain and imprison",
                "punch the daylights out of",
                "launch nukes against",
                "send an army against",
                "launch an investigation against",
                "personally find and laugh at"));

        for (String item: verbListNegative){
            verbs.add(new Verb(item, -1));
        }


        //Positive nouns that are not amountable
        List<String> nounListPositiveNA = new ArrayList<String>(Arrays.asList(
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
                "puppies, specifically the ones at the Downtown Toronto Dog Shelter",
                "Boundless Peacocks, the very last of their species",
                "sad computer science students at the University of Toronto"));

        for (String item: nounListPositiveYA){
            nouns.add(new Noun(item, 1, true));
        }


        //Negative nouns that are not amountable
        List<String> nounListNegativeNA = new ArrayList<String>(Arrays.asList(
                "the premier of Ontario, Canada",
                "the leader of North Korea",
                "Colin, a medical practitioner found to have cheated on his medical exams after a related illegal nose smuggling ring was busted",
                "Kavin, a phantom thief who masterminded the theft all the laptop chargers, but not the laptops, at the University of Toronto last Fall"));

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
        //For when we have a male or female player
        String pronoun = "sir";


        int verbIndex = (int)(Math.random() * (verbs.size()));
        int nounIndex = (int)(Math.random() * (nouns.size()));
        List<String> promptBeginList = new ArrayList<String>(Arrays.asList(
                "Good afternoon " + pronoun + ", our campaign researchers speculate that it may be in our best interests to declare that you would",
                "This just in " + pronoun + ", there are rumors that some of our more excited supporters have been advocating that you would",
                "I'm here for a briefing " + pronoun + ", there seems to be a growing support for campaign leaders who would",
                "Greetings " + pronoun + ", here to drop off a report about our supporters in the west. It seems that there are rumors that you would",
                "Nice to meet you " + pronoun + ". I am one of your many campaign assistants and was wondering if you would advocate to"));

        String promptBegin = promptBeginList.get((int)(Math.random() * (promptBeginList.size())));

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

    public int getCurrentPromptScore(){return currentPrompt.getCategory();}

    public int getPromptsDone(){return prompts.size();}
}
