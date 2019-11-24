package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.Games.SpeechGame.SpeechInstructionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BabyArcade extends BabyGameMode{

    BabyArcade(PoliticGameApp app){
        super(app);
    }

    public Intent next(Context lastActivity){
        Intent switchSpeechIntent = new Intent(lastActivity, SpeechInstructionActivity.class);
        return switchSpeechIntent;
    }

    public void save(int score){
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
                    JSONObject levelObj = charObject.getJSONObject(charName).getJSONObject(LEVEL_NAME);
                    levelObj.put("score", score);
                    levelObj.put("complete", true);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        currentUser.saveToDb();
    }
}
