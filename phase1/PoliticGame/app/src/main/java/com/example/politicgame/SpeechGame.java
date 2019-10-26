package com.example.politicgame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class SpeechGame {

    private int points;
    private String input;
    private String prompt;
    private String relevantWord;

    private final int POINTSGIVEN = 1;
    private final String PATH = "app\\assets\\SpeechPrompts.txt";

    public SpeechGame() {
        this.points = 0;
        this.input = "";
        this.prompt = "";
        this.relevantWord = "";
        readFile();
    }

    private void readFile(){
        File text = new File(PATH);
        Scanner sc = new Scanner("");
        try {
            sc = new Scanner(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.prompt = sc.nextLine();
        sc.useDelimiter(",");
        while(sc.hasNext()){
            String word = sc.next();
            if (word.charAt(0) != '*') {
                relevantWord = word;
            }
        }
    }

    public void setInput(String input) {
        this.input = input.toLowerCase();
    }

    private void awardPoints(){
        if (input.equals(relevantWord)){
            this.points += POINTSGIVEN;
        }
    }

    public int getPoints() {
        return points;
    }

    public String getPrompt() {
        return prompt;
    }
}
