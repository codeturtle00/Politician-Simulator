package com.example.politicgame.Character.UserTools;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class UserAccountResetLevels {
    UserAccountResetLevels() {}

    void resetLevels(JSONArray charArray, String charName) {
        try {
            for (int i = 0; i < charArray.length(); i++) {
                JSONObject currentChar = charArray.getJSONObject(i);
                String currName = currentChar.keys().next();
                if (currName.equals(charName)) {
                    JSONObject characterInfo = currentChar.getJSONObject(charName);

                    JSONObject level1 = characterInfo.getJSONObject("LEVEL1");
                    Log.i("Level 1", level1.toString());
                    level1.remove("score");
                    level1.put("complete", false);

                    JSONObject level2 = characterInfo.getJSONObject("LEVEL2");
                    Log.i("Level 2", level2.toString());
                    level2.remove("score");
                    level2.put("complete", false);

                    JSONObject level3 = characterInfo.getJSONObject("LEVEL3");
                    Log.i("Level 3", level3.toString());
                    level3.remove("score");
                    level3.put("complete", false);

                    Log.i("Character Info", characterInfo.toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
