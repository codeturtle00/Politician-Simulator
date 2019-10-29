package com.example.politicgame.User;

import org.json.JSONArray;

public class Character {
    private String name;
    private int score;
    private JSONArray levelScore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public JSONArray getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(JSONArray levelScore) {
        this.levelScore = levelScore;
    }
}
