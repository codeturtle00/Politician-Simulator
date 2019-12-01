package com.example.politicgame.Character;

import com.example.politicgame.Builder.CharacterBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameCharacter {
  private int charId; // set a default character

  public GameCharacter(int charId) {
    this.charId = charId;
  }

    /**
     * Returns a new JSONObject of the current character to be saved
     *
     * @param name  The name of the character to create the JSONObject for
     * @return      The JSONObject for the character
     */
  public JSONObject getJsonChar(String name) {
    CharacterBuilder charBuilder = new CharacterBuilder(name, charId);
    JSONObject charJson = charBuilder.getJsonChar();

    return charJson;
  }
}
