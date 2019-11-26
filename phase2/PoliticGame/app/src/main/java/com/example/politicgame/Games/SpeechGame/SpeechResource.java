package com.example.politicgame.Games.SpeechGame;

import java.io.Serializable;
import java.util.ArrayList;

public class SpeechResource implements Serializable {
    public ArrayList<String> getPrompt() {
        return prompt;
    }

    public ArrayList<ArrayList<String>> getChoice() {
        return choice;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    private ArrayList<String> prompt;
    private ArrayList<ArrayList<String>> choice;
    private ArrayList<String> answer;

    public int getDataBaseNum() {
        return dataBaseNum;
    }

    private int dataBaseNum;

    SpeechResource() {
        this.prompt = new ArrayList();
        this.choice = new ArrayList();
        this.answer = new ArrayList();
        setPrompt();
        setAnswer();
        setChoice();
        this.dataBaseNum = this.prompt.size();
    }

    private void setPrompt() {
        this.prompt.add(
                "The country needs more budget to spend on improving public education and there is discussion for raising the money");
        this.prompt.add(
                "Many young people in the country are struggling to pay back student loans and there is discussion for the government to relieve this debt");
        this.prompt.add(
                "Climate change is rising threat to the planet and there is discussion to expand on the country's renewable energy sources");
        this.prompt.add(
                "The homelessness rate in the country is rising each year and there is discussion to introduce a universal basic income");
        this.prompt.add(
                "Surveys show that many citizens do not like their governors on the provincial and municipal level and there is discussion to introduce a term limit");
        this.prompt.add(
                "A recent trend shows that many corporations are outsourcing jobs to other parts of the world and there is discussion so keep jobs domestic");
        this.prompt.add(
                "Genetically modified foods is a controversial topic and there is discussion to heavily regulate them or ban them");
        this.prompt.add(
                "New strains of diseases are on the rise and there is discussion for the government to introduce mandatory vaccinations");
        this.prompt.add(
                "Studies have shown that a 4 day work week increases productivity and there is discussion for this change to be implemented");
        this.prompt.add(
                "Older citizens are struggling to retire at reasonable age and there is discussion to increase senior assissance");
    }

    private void setChoice() {
        this.helpSetChoice("taxes", "environment", "religion", "military");
        this.helpSetChoice("immigration", "healthcare", "corporations", "taxes");
        this.helpSetChoice("energy", "economy", "unions", "trade");
        this.helpSetChoice("immigration", "poverty", "internet", "security");
        this.helpSetChoice("voting", "education", "taxes", "medication");
        this.helpSetChoice("diversity", "religion", "healthcare", "economy");
        this.helpSetChoice("energy", "corporations", "agriculture", "voting");
        this.helpSetChoice("trade", "military", "education", "healthcare");
        this.helpSetChoice("security", "corporations", "immigration", "poverty");
        this.helpSetChoice("pension", "internet", "military", "diversity");
    }

    private void helpSetChoice(String A, String B, String C, String D) {
        ArrayList<String> array = new ArrayList<String>();
        array.add(A);
        array.add(B);
        array.add(C);
        array.add(D);
        choice.add(array);
    }

    private void setAnswer() {
        this.answer.add("taxes");
        this.answer.add("taxes");
        this.answer.add("energy");
        this.answer.add("poverty");
        this.answer.add("voting");
        this.answer.add("economy");
        this.answer.add("agriculture");
        this.answer.add("healthcare");
        this.answer.add("corporations");
        this.answer.add("pension");
    }
}
