package com.example.politicgame.Character;

import com.example.politicgame.Builder.CharacterBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameCharacter {
    private int charId ;// set a default character
    public GameCharacter(int charId){
        this.charId = charId;
    }
  public JSONObject getJsonChar(String name){
      CharacterBuilder charBuilder = new CharacterBuilder(name);
      JSONObject charJson = charBuilder.getJsonChar();
      try {
          charJson.put("charId",this.charId);
      } catch (JSONException e) {
          e.printStackTrace();
      }
      return charJson;
  }
}
