package com.example.politicgame.Leaderboard;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class SingleBoardGetter extends BoardLoader{
    private String levelName;

    SingleBoardGetter(Context lastContext, String levelName){
        super(lastContext);
        this.levelName = levelName;
    }

    /**
     * Returns a JSONArray containing scores, characters and users based off of the initially
     * constructed levelName
     *
     * @return  A JSONArray containing JSONObjects that represent the scores and, the user and
     *          character that achieved it
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

                        Log.i("Character Array", currentCharacter.getJSONObject(charName).toString());

                        JSONObject singleScores = currentCharacter.getJSONObject(charName).getJSONObject("SingleScores");
                        JSONArray levelScores = singleScores.getJSONArray(levelName);

                        for (int k = 0; k < levelScores.length(); k++) {
                            JSONObject charScore = new JSONObject();
                            JSONObject charInfo = new JSONObject();

                            charInfo.put("userName", userKey);
                            charInfo.put("score", levelScores.getInt(k));
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
