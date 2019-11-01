package com.example.politicgame.Character;

import org.json.JSONException;
import org.json.JSONObject;

public class PoliticianB extends GameCharacter {
  private int charId = 2;
  @Override
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

  @Override
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

  @Override
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

  @Override
  public JSONObject getJsonCharacter() {
    JSONObject politic = super.getJsonCharacter();
    try{
      politic.getJSONObject(getName()).put(detail.charId.toString(),this.charId);
    }
    catch (JSONException e){
      e.printStackTrace();
    }
    return politic;
  }
}
