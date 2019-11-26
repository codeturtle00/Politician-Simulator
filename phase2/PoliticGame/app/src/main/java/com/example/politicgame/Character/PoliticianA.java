package com.example.politicgame.Character;

import org.json.JSONException;
import org.json.JSONObject;

public class PoliticianA extends GameCharacter {
  private int charId = 1;

  /** Gets the JSON data for level 1. */
    protected JSONObject getJsonLevel1() {
    JSONObject detailObject = new JSONObject();
    try {

      JSONObject statsObject = new JSONObject();
      // TODO: Specify what data to track in level1 by initializing statsObject
      statsObject.put(detail.Rating.toString(), 0);
      detailObject.put(detail.LEVEL1.toString(), statsObject);

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return detailObject;
  }

  /** Gets the JSON data for level 2. */
  protected JSONObject getJsonLevel2() {
    JSONObject detailObject = new JSONObject();
    try {

      JSONObject statsObject = new JSONObject();
      // TODO: Specify what data to track in level2 by initializing statsObject
      statsObject.put(detail.Rating.toString(), 0);
      detailObject.put(detail.LEVEL2.toString(), statsObject);

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return detailObject;
  }

  /** Gets the JSON data for level 3. */
  protected JSONObject getJsonLevel3() {
    JSONObject detailObject = new JSONObject();
    try {

      JSONObject statsObject = new JSONObject();
      // TODO: Specify what data to track in level3 by initializing statsObject
      statsObject.put(detail.Rating.toString(), 0);
      detailObject.put(detail.LEVEL3.toString(), statsObject);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return detailObject;
  }


  /** Gets the character being used. */
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
