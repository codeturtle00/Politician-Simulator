package com.example.politicgame.Character.UserTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class UserAccountAddScore {
    UserAccountAddScore() {}

    void addScore(JSONArray charArray, String charName, int score) {
        try {
                for (int i = 0; i < charArray.length(); i++) {
                    JSONObject currentChar = charArray.getJSONObject(i);
                    String currName = currentChar.keys().next();
                    if (currName.equals(charName)) {
                        currentChar.getJSONObject(charName).getJSONArray("SCORE").put(score);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
        }
    }
}
