package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.Character.UserTools.UserAccount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public abstract class ArcadeMode implements GameMode, Serializable {
    /*protected PoliticGameApp app;*/
    private String levelName;

    public ArcadeMode(/*PoliticGameApp app,*/ String level_name){
        /*this.app = app;*/
        this.levelName = level_name;
    }


    /**
     * Returns the Intent to the next required activity
     *
     * @param lastActivity  The activity this class was instantiated in
     * @return              The Intent to the next required activity
     */
    public abstract Intent next(Context lastActivity);


    /**
     * Saves the score for the child's level. Example: BabyArcade extends ArcadeMode and level_name
     * will be passed on from BabyArcade, which will tell this class where to save to
     *
     * @param score
     */
    public void save(PoliticGameApp app, int score){
        UserAccount currentUser = app.getCurrentUser();
        String currentCharacterName = app.getCurrentCharacter();

        JSONArray charArray = currentUser.getCharArray();

        Log.i("Get existing characters", charArray.toString());

        try {
            int i;
            for (i = 0; i < charArray.length(); i++) {
                JSONObject charObject = charArray.getJSONObject(i);
                String charName = charObject.keys().next();

                if (charName.equals(currentCharacterName)) {
                    JSONObject levelObj = charObject.getJSONObject(charName).getJSONObject(levelName);
                    levelObj.put("score", score);
                    levelObj.put("complete", true);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        currentUser.saveToDb();
    }

    /**
     * Determines if the user has played the given level and records it.
     *
     * @return if the user has played through the level
     */
    public boolean isGameComplete(PoliticGameApp app) {
        Log.i("ArcadeMode", String.valueOf(app == null));
        UserAccount currentUser = app.getCurrentUser();
        String currentCharacterName = app.getCurrentCharacter();

        JSONArray charArray = currentUser.getCharArray();

        Log.i("Get existing characters", charArray.toString());

        boolean isComplete = false;

        try {
            int i;
            for (i = 0; i < charArray.length(); i++) {
                JSONObject charObject = charArray.getJSONObject(i);
                String charName = charObject.keys().next();

                if (charName.equals(currentCharacterName)) {
                    JSONObject levelObj = charObject.getJSONObject(charName).getJSONObject(levelName);
                    isComplete = levelObj.getBoolean("complete");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return isComplete;
    }
}
