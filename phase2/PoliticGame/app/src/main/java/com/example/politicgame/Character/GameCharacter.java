package com.example.politicgame.Character;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class GameCharacter {
  private String name = "Trump";

  enum detail {
    LEVEL1,
    LEVEL2,
    LEVEL3,
    SCORE,
    Rating,
    charId,
    SingleScores
  }

  /** Gets the JSON data for level 1. */
  private JSONObject getJsonLevel1() {
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
  private JSONObject getJsonLevel2() {
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
  private JSONObject getJsonLevel3() {
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

  /**
   *
   * @return
   */
  private JSONObject getJsonScore() {
    JSONObject detailObject = new JSONObject();
    try {
      JSONArray statsArray = new JSONArray();
      detailObject.put(detail.SCORE.toString(), statsArray);

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return detailObject;
  }


  /**SingleScores: {
   *      LEVEL1:{[]},
   *      LEVEL2:{[]},
   *      LEVEL3:{[]}
   *  }
   */
  private JSONObject getJsonSingles() {
    JSONObject allLevels = new JSONObject();
    JSONObject singleLevels = new JSONObject();

    try {
      singleLevels.put(detail.LEVEL1.toString(), new JSONArray());
      singleLevels.put(detail.LEVEL2.toString(), new JSONArray());
      singleLevels.put(detail.LEVEL3.toString(), new JSONArray());
      allLevels.put(detail.SingleScores.toString(), singleLevels);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return allLevels;
  }

  /**
   * "LEVEL1":{}...
   *
   * <p>"LEVEL1":{"complete":false}
   */
  public JSONObject getJsonCharacter() {
    JSONObject charObject = new JSONObject();
    try {
      JSONObject detailObject = new JSONObject();
      detailObject.put(detail.LEVEL1.toString(), getJsonLevel1().get(detail.LEVEL1.toString()));
      detailObject.put(detail.LEVEL2.toString(), getJsonLevel2().get(detail.LEVEL2.toString()));
      detailObject.put(detail.LEVEL3.toString(), getJsonLevel3().get(detail.LEVEL3.toString()));
      detailObject.put(detail.SCORE.toString(), getJsonScore().get(detail.SCORE.toString()));
      detailObject.put(detail.SingleScores.toString(), getJsonSingles().get(detail.SingleScores.toString()));
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
