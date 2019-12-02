package com.example.politicgame.GameMode;

import com.example.politicgame.Character.UserTools.UserAccount;

class SaveInfo {
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
    SaveInfo(UserAccount currentUser, String charName, int totalScore){
        this.currentUser = currentUser;
        this.charName = charName;
        this.totalScore = totalScore;
    }

    /**
     * Saves the loaded info to the current user's character
     */
    void saveInfo(){
        currentUser.addScore(charName, totalScore);
        currentUser.resetLevels(charName);
        currentUser.saveToDb();
    }

    /**
     * Reset the level data for the current user's character
     */
    void resetLevels (){
        currentUser.resetLevels(charName);
        currentUser.saveToDb();
    }

    /**
     * Saves the info for a single-game mode game
     *
     * @param levelName The name of the level we are saving to
     */
    void singleSaveInfo(String levelName){
        currentUser.singleSave(levelName, charName, totalScore);
        currentUser.saveToDb();
    }

    /**
     * Returns the current character's name
     *
     * @return  The name of the current character
     */
    public String getCharName() {
        return charName;
    }

    /**
     * Sets the new current character
     *
     * @param charName  The name of the new character
     */
    public void setCharName(String charName) {
        this.charName = charName;
    }
}
