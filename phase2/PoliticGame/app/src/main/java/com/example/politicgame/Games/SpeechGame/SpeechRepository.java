package com.example.politicgame.Games.SpeechGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpeechRepository implements Serializable {

    /**Returns the ArrayList containing the prompts
     *
     * @return prompt: ArrayList containing all the game prompts to be answered
     */
    ArrayList<String> getPrompt() {
        return prompt;
    }

    /**Returns the ArrayList containing the choices
     *
     * @return choice: ArrayList containing all the game choices to be answered
     */
    ArrayList<ArrayList<String>> getChoice() {
        return choice;
    }

    /**Returns the ArrayList containing the answers
     *
     * @return answer: ArrayList containing all the correct game answers
     */
    public ArrayList<String> getAnswer() {
        return answer;
    }

    private ArrayList<String> prompt = new ArrayList();
    private ArrayList<ArrayList<String>> choice = new ArrayList();
    private ArrayList<String> answer = new ArrayList();
    private SpeechResource speechResource;

    /**Randomly mixes up the order for which the prompts, choices, and answers are loaded out
     *
     * @param numRange: int representing the number of questions present
     * @return fullRange: a List of integers representing indices to mix up the order
     * of prompt, choice, and answer
     */
    private List<Integer> randomSelection(int numRange) {
        List<Integer> fullRange = new ArrayList<>();
        for (int i = 0; i <= numRange; i++) {
            fullRange.add(i);
        }
        if (fullRange.size() > numRange) {
            Collections.shuffle(fullRange);
            fullRange.remove(0);
        }
        return fullRange;
    }

    /**
     * Constructor that initializes a SpeechResource object
     */
    SpeechRepository() {
        this.speechResource = new SpeechResource();
    }

    /**Initializes prompt, choice, and answer
     *
     * @param numRange: int representing the number of questions to be used
     */
    void loadQuestions(int numRange) {
        List<Integer> numRanges = randomSelection(Math.min(numRange, speechResource.getDataBaseNum() - 1));
        for (int i : numRanges) {
            prompt.add(this.speechResource.getPrompt().get(i));
            choice.add(this.speechResource.getChoice().get(i));
            answer.add(this.speechResource.getAnswer().get(i));
        }
    }
}
