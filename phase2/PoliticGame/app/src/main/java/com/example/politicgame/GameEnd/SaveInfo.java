package com.example.politicgame.GameEnd;

import com.example.politicgame.Character.UserTools.UserAccount;

public class SaveInfo {
    private UserAccount currentUser;
    private String charName;
    private int totalScore;

    /**
     * Constructor, initializes the instance variables here
     *
     * @param currentUser   The current UserAccount object, used to identify the User to save to
     * @param charName      The currently selected character, used to identify the character to save to
     * @param totalScore    The total score for that character's playthrough
     */
    public SaveInfo(UserAccount currentUser, String charName, int totalScore){
        this.currentUser = currentUser;
        this.charName = charName;
        this.totalScore = totalScore;
    }

    /**
     * Saves the loaded info to the current user's character
     */
    public void saveInfo(){
        currentUser.addScore(charName, totalScore);
        currentUser.resetLevels(charName);
        currentUser.saveToDb();
    }

    public void resetLevels (){
        currentUser.resetLevels(charName);
        currentUser.saveToDb();
    }

    public UserAccount getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserAccount currentUser) {
        this.currentUser = currentUser;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
