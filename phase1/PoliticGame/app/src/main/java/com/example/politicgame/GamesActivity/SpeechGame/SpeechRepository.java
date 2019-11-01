package com.example.politicgame.GamesActivity.SpeechGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpeechRepository {
  public ArrayList<String> getPrompt() {
    return prompt;
  }

  public ArrayList<ArrayList<String>> getChoice() {
    return choice;
  }

  public ArrayList<String> getAnswer() {
    return answer;
  }

  private ArrayList<String> prompt = new ArrayList();
  private ArrayList<ArrayList<String>> choice = new ArrayList();
  private ArrayList<String> answer = new ArrayList();
  private SpeechResource speechResource;

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

  SpeechRepository() {
    this.speechResource = new SpeechResource();
  }

  public void loadQuestions(int numRange) {
    List<Integer> numRanges = randomSelection(Math.min(numRange, speechResource.getDataBaseNum()-1));
    for (int i : numRanges) {
      prompt.add(this.speechResource.getPrompt().get(i));
      choice.add(this.speechResource.getChoice().get(i));
      answer.add(this.speechResource.getAnswer().get(i));
    }
    System.out.println(prompt);
    System.out.println(choice);
    System.out.println(answer);
  }
}
