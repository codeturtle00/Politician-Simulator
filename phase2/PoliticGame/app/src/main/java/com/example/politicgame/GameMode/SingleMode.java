package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.GameEnd.SingleEndActivity;
import com.example.politicgame.Games.SpeechGame.SpeechInstructionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class SingleMode implements GameMode, Serializable {
    private String levelName;
    private int score;

    SingleMode(String level_name){
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


    public boolean isGameComplete (PoliticGameApp app){return false;}
}
