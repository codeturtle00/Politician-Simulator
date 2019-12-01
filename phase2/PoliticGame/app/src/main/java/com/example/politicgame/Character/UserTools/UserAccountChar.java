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

  /**
   * Returns the User's display name
   *
   * @return  The User's display name
   */
  String getDisplayName() {
    return displayName;
  }

  /**
   * Sets the current character
   *
   * @param currentCharacter The current character as an instance of GameCharacter
   */
  void setCurrentCharacter(GameCharacter currentCharacter) {
    this.currentCharacter = currentCharacter;
  }

  /**
   * Returns the instance of the current character
   *
   * @return  The current character as an instance of GameCharacter
   */
  GameCharacter getCurrentCharacter() {
    return currentCharacter;
  }

  /**
   * Sets the charArray, the character's information array, array passed in from the argument
   *
   * @param charArray The JSONArray that replaces charArray
   */
  void setCharArray(JSONArray charArray) {
    this.charArray = charArray;
  }

  /**
   * Returns charArray
   *
   * @return  The current charArray
   */
  JSONArray getCharArray() {
    return this.charArray;
  }

  /**
   * Adds a new object into the charArray
   *
   * @param charObject  The JSONObject to be added
   */
  void addCharArray(JSONObject charObject) {
    this.charArray.put(charObject);
  }

  /**
   * Returns the character id of the character requested through the parameter, which is used
   * to differentiate which character the user selected at the beginning of the game
   *
   * @param charName  The character's name
   * @return  The character id of the character requested
   */
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

  /**
   * Checks if the user already has a character with the same name
   *
   * @param charName The character's name whom we are checking for
   * @return  If the name passed in the argument already exists
   */
  boolean isDuplicate(String charName) {
    try {
      for (int i = 0; i < charArray.length(); i++) {
        JSONObject charInfo = charArray.getJSONObject(i);
        String currName = charInfo.keys().next();

        Log.i("Checking Character ID", charInfo.toString());

        if (currName.equals(charName)) {
          Log.i("charId Object", "The name is a duplicate");
          return true;
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Delete the character specified in the argument
   *
   * @param charName  The character to erase by name
   */
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

  /**
   * Returns a JSONObject of the character, containing their information on levels, scores, etc.
   *
   * @param charName  The name of the character we are finding info for
   * @return  The JSONObject representing the character requested, the object found in user.json
   */
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
