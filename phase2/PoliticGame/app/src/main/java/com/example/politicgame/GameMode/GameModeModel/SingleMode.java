package com.example.politicgame.GameMode.GameModeModel;

import android.content.Context;
import android.content.Intent;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.GameMode.SingleEndActivity;

public class SingleMode implements GameMode {
    private String levelName;
    private int score;

    public SingleMode(String level_name){
        this.levelName = level_name;
    }

    /**
     * Move on to the results screen with the score and level name stored
     *
     * @param lastActivity  The activity that this method was instantiated from
     * @return              The Intent that moves to the next screen that also contains info needed to save
     */
    public Intent next(Context lastActivity){
        Intent endGameIntent = new Intent(lastActivity, SingleEndActivity.class);
        endGameIntent.putExtra("score", this.score);
        endGameIntent.putExtra("level_name", this.levelName);
        return endGameIntent;
    }

    /**
     * Assigns the score value to an instance variable which will be passed onto the Results screen
     * where the user can choose to save the results or not
     *
     * @param score The score received in the last game
     */
    public void save(PoliticGameApp app, int score){
        this.score = score;
    }


    /**
     * Returns if the game is complete, in any single-game mode's case this will always return false
     * as this is used to check if we should skip games to move on
     *
     * @param app           The current instance of PoliticGameApp. This needs to be declared here
     *                      as the arcade mode's saving requires access to PoliticGameApp, but the
     *                      class cannot be Serializable if it is declared as an instance variable
     *                      as children of the Application class are not Serializable and thus
     *                      cannot be included as fields in Serializable classes
     *                      cannot be included as fields in Serializable classes
     *
     * @return  false, will always return false for SingleMode.java
     */
    public boolean isGameComplete (PoliticGameApp app){return false;}
}
