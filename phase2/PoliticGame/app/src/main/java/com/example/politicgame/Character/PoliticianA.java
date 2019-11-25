package com.example.politicgame.Character;

import org.json.JSONException;
import org.json.JSONObject;

public class PoliticianA extends GameCharacter {
  private int charId = 1;

  /** Gets the character being used. */
  @Override
  public JSONObject getJsonCharacter() {
    JSONObject politic = super.getJsonCharacter();
    try {
      // Set the character ID to 1
      politic.getJSONObject(getName()).put(detail.charId.toString(), this.charId);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return politic;
  }
}
