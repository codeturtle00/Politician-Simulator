package com.example.politicgame.Character.UserTools;

import android.util.Log;

import com.example.politicgame.Character.GameCharacter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class UserAccountChar {
  private String displayName;
  private JSONArray charArray = new JSONArray();
  private GameCharacter currentCharacter;

  UserAccountChar(String displayName) {
    this.displayName = displayName;
  }

  String getDisplayName() {
    return displayName;
  }

  void setCurrentCharacter(GameCharacter currentCharacter) {
    this.currentCharacter = currentCharacter;
  }

  GameCharacter getCurrentCharacter() {
    return currentCharacter;
  }

  void setCharArray(JSONArray charArray) {
    this.charArray = charArray;
  }

  JSONArray getCharArray() {
    return this.charArray;
  }

  void addCharArray(JSONObject charObject) {
    this.charArray.put(charObject);
  }

  int getCharId(String charName) {
    try {
      for (int i = 0; i < charArray.length(); i++) {
        JSONObject charInfo = charArray.getJSONObject(i);
        String currName = charInfo.keys().next();

        if (currName.equals(charName)) {
          Log.i("charId Object", charInfo.getJSONObject(charName).toString());
          return charInfo.getJSONObject(charName).getInt("charId");
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return 0;
  }

  void deleteCharByName(String charName) {
    try {
      for (int i = 0; i < charArray.length(); i++) {
        JSONObject currentChar = charArray.getJSONObject(i);
        String currName = currentChar.keys().next();
        if (currName.equals(charName)) {
          charArray.remove(i);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  JSONObject getCharByName(String charName) {
    try {
      for (int i = 0; i < charArray.length(); i++) {
        JSONObject currentChar = charArray.getJSONObject(i);
        String currName = currentChar.keys().next();
        if (currName.equals(charName)) {
          return currentChar;
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return new JSONObject();
  }
}
