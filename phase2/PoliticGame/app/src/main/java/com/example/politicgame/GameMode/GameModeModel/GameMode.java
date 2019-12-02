package com.example.politicgame.GameMode.GameModeModel;

import android.content.Context;
import android.content.Intent;

import com.example.politicgame.Application.PoliticGameApp;

import java.io.Serializable;

public interface GameMode extends Serializable {

    /**
     * Returns an intent which directs the last activity to the next required activity. Depending on
     * the class the implements this, it could either lead to the single-game mode results screen or
     * the next activity in the arcade mode.
     *
     * ARCADE MODE:         Returns an intent which leads you to the next game or the results screen
     *                      if you call this from the StampGame
     *
     * SINGLE-GAME MODE:    Returns an intent which leads you to the single-game mode results page
     *
     * @param lastActivity  The activity that this method was called from, used to create Intent
     * @return              The intent that leads to the next activity
     */
    public Intent next(Context lastActivity);


    /**
     * Saves the data for the current game. Depending on the game mode it saves to different places
     * and saves the information differently.
     *
     * ARCADE MODE:         Saves the information received here to the individual level locations,
     *                      sets the scores, sets the game to be complete and makes it so that any
     *                      calls of next() on the current game will skip over on the same
     *                      playthrough
     *
     * SINGLE-GAME MODE:    Saves the score to an instance variable, where the score will actually
     *                      be saved in the results screen.
     *
     * @param app           The current instance of PoliticGameApp. This needs to be declared here
     *                      as the arcade mode's saving requires access to PoliticGameApp, but the
     *                      class cannot be Serializable if it is declared as an instance variable
     *                      as children of the Application class are not Serializable and thus
     *                      cannot be included as fields in Serializable classes
     * @param score         The score to be saved
     */
    public void save(PoliticGameApp app, int score);


    /**
     * Returns if this game has already been completed for this current play-through
     *
     * ARCADE MODE:         Returns if the current level has been complete in the current
     *                      play-through in a boolean expression
     *
     * SINGLE-GAME MODE:    Returns false, as single-game mode has you play the game once and thus
     *                      you cannot of retries of play-throughs
     *
     * @param app           The current instance of PoliticGameApp. This needs to be declared here
     *                      as the arcade mode's saving requires access to PoliticGameApp, but the
     *                      class cannot be Serializable if it is declared as an instance variable
     *                      as children of the Application class are not Serializable and thus
     *                      cannot be included as fields in Serializable classes
     *                      cannot be included as fields in Serializable classes
     *
     * @return              Returns if the game is complete for this play-through
     */
    public boolean isGameComplete(PoliticGameApp app);
}
