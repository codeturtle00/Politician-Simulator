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
        this.input = "";
        this.prompt = "";
        this.relevantWord = "";
        readFile();
    }

    private void readFile() {
        File text = new File(PATH);
        Scanner sc = new Scanner("");
        try {
            sc = new Scanner(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            this.prompt = sc.nextLine();
            sc.useDelimiter(",");
            while (sc.hasNext()) {
                String word = sc.next();
                if (word.charAt(0) != '*') {
                    relevantWord = word;
                }
            }
            sc.useDelimiter("\n");
        }
    }

    public void setInput(String input) {
        this.input = input.toLowerCase();
    }

    private boolean awardPoints() {
        return input.equals(relevantWord);
    }

    public String getPrompt() {
        return prompt;
    }
}
