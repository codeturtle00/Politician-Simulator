package com.example.politicgame.Character;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class GameCharacter {
  private String name = "Trump";
  enum detail {
    LEVEL1,
    LEVEL2,
    LEVEL3,
    SCORE,
    Rating
  }

  protected abstract JSONObject getJsonLevel1();

  protected abstract JSONObject getJsonLevel2();

  protected abstract JSONObject getJsonLevel3();

  protected JSONObject getJsonScore() {
    JSONObject detailObject = new JSONObject();
    try {
      JSONObject statsObject = new JSONObject();
      detailObject.put(detail.SCORE.toString(), statsObject);

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return detailObject;
  }

  public JSONObject getJsonCharacter() {
    JSONObject charObject = new JSONObject();
    try {
      JSONObject detailObject = new JSONObject();
      detailObject.put(detail.LEVEL1.toString(),getJsonLevel1().get(detail.LEVEL1.toString()));
      detailObject.put(detail.LEVEL2.toString(),getJsonLevel2().get(detail.LEVEL2.toString()));
      detailObject.put(detail.LEVEL3.toString(),getJsonLevel3().get(detail.LEVEL3.toString()));
      detailObject.put(detail.SCORE.toString(),getJsonScore().get(detail.SCORE.toString()));
      charObject.put(getName(), detailObject);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return charObject;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
