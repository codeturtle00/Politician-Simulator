package com.example.politicgame.GamesActivity.SpeechGame;

import java.util.ArrayList;

public class SpeechResource {
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
    SpeechResource(){
        this.prompt = new ArrayList();
        this.choice = new ArrayList();
        this.answer = new ArrayList();
        setPrompt();
        setAnswer();
        setChoice();
        this.dataBaseNum = this.prompt.size();
    }
    private void setPrompt(){
        this.prompt.add("The country needs more budget to spend on improving public education and there is discussion for raising the money");
        this.prompt.add("Many young people in the country are struggling to pay back student loans and there is discussion for the government to relieve this debt");
        this.prompt.add("Climate change is rising threat to the planet and there is discussion to expand on the country's renewable energy sources");
        this.prompt.add("The homelessness rate in the country is rising each year and there is discussion to introduce a universal basic income");
        }
        private void setChoice(){
        this.helpSetChoice("taxes","environment","religion","military");
        this.helpSetChoice("immigration","healthcare","corporations","taxes");
        this.helpSetChoice("energy","economy","unions","trade");
        this.helpSetChoice("immigration","poverty","internet","security");
        }
        private void helpSetChoice(String A,String B, String C, String D){
        ArrayList<String> array = new ArrayList<String>();
        array.add(A);
        array.add(B);
        array.add(C);
        array.add(D);
        choice.add(array);
    }
      private void setAnswer(){
        this.answer.add("taxes");
        this.answer.add("taxes");
        this.answer.add("energy");
        this.answer.add("poverty");
      }
}

