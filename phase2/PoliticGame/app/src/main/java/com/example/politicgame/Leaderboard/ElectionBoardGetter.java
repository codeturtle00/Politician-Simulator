package com.example.politicgame.Leaderboard;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

class ElectionBoardGetter extends BoardLoader{
    ElectionBoardGetter(Context lastContext){
        super(lastContext);
    }

    /**
     * Returns the scores as a JSONArray so they can be sorted
     *
     * @return  The scores for Election mode playthroughs
     */
    JSONArray getScores(){
        JSONArray jsonList = this.fileSaving.readJsonFile(FILE_NAME);
        JSONArray boardList = new JSONArray();

        try {
            // A JSON files containing the User, their characters and their scores
            for (int i = 0; i < jsonList.length(); i++) {
                Iterator<String> userKeys = jsonList.getJSONObject(i).keys();
                while (userKeys.hasNext()) {
                    String userKey = userKeys.next(); // String version of the userName
                    JSONArray charArray = jsonList.getJSONObject(i).getJSONArray(userKey);

                    for (int j = 0; j < charArray.length(); j++) {
                        JSONObject currentCharacter = charArray.getJSONObject(j);
                        String charName = currentCharacter.keys().next();

                        JSONArray scores = currentCharacter.getJSONObject(charName).getJSONArray("SCORE");

                        for (int k = 0; k < scores.length(); k++) {
                            JSONObject charScore = new JSONObject();
                            JSONObject charInfo = new JSONObject();

                            charInfo.put("userName", userKey);
                            charInfo.put("score", scores.getInt(k));
                            charScore.put(charName, charInfo);

                            Log.i("Character Score", charScore.toString());

                            boardList.put(charScore);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("Character Scores", boardList.toString());

        return boardList;
    }
}
