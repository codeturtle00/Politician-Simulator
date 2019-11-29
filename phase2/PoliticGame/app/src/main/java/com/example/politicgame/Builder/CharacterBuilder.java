package com.example.politicgame.Builder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is to build a character into json This is a sample code of how you can use this class:
 *
 * @code JsonObject charBuilder= new CharacterBuilder("charName") charBuilder.getJsonChar() Pass in
 *     the name of the name of the cahracter and get a JsonObject formatted in the way stored inn
 *     database
 */
public class CharacterBuilder {
  private JSONObject jsonChar;

  public JSONObject getJsonChar() {
    return jsonChar;
  }

  enum detail {
    LEVEL1,
    LEVEL2,
    LEVEL3,
    SCORE,
    charId,
    SingleScores
  }

  public CharacterBuilder(String name) {
    this.jsonChar = new JSONObject();
    try {
      JSONObject detailObject = new JSONObject();
      detailObject.put(detail.LEVEL1.toString(),
          getJsonLevel1().get(detail.LEVEL1.toString()));
      detailObject.put(detail.LEVEL2.toString(),
          getJsonLevel2().get(detail.LEVEL2.toString()));
      detailObject.put(detail.LEVEL3.toString(),
          getJsonLevel3().get(detail.LEVEL3.toString()));
      detailObject.put(detail.SCORE.toString(),
          getJsonScore().get(detail.SCORE.toString()));
      detailObject.put(detail.SingleScores.toString(),
          getJsonSingles().get(detail.SingleScores.toString()));
      this.jsonChar.put(name, detailObject);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /** Helper method to create a nested json object for user,given name
   * Note :if tracking more data for each level,we will change stataObject
   * inside this helper method
   * {"LEVEL1":{}} */
  private JSONObject createNestedJsonObject(String name) {
    JSONObject detailObject = new JSONObject();
    try {
      JSONObject statsObject = new JSONObject();
      detailObject.put(name, statsObject);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return detailObject;
  }

  /** Gets the JSON data for level 1. */
  private JSONObject getJsonLevel1() {
    return createNestedJsonObject(detail.LEVEL1.toString());
  }

  /** Gets the JSON data for level 2. */
  private JSONObject getJsonLevel2() {
    return createNestedJsonObject(detail.LEVEL2.toString());
  }

  /** Gets the JSON data for level 3. */
  private JSONObject getJsonLevel3() {
    return createNestedJsonObject(detail.LEVEL3.toString());
  }

  /** Gets the JSON data for score. */
  private JSONObject getJsonScore() {
    return createNestedJsonObject(detail.SCORE.toString());
  }

  /** SingleScores: { LEVEL1:{[]}, LEVEL2:{[]}, LEVEL3:{[]} } */
  private JSONObject getJsonSingles() {
    JSONObject allLevels = new JSONObject();
    JSONObject singleLevels = new JSONObject();

    try {
      singleLevels.put(CharacterBuilder.detail.LEVEL1.toString(), new JSONArray());
      singleLevels.put(CharacterBuilder.detail.LEVEL2.toString(), new JSONArray());
      singleLevels.put(CharacterBuilder.detail.LEVEL3.toString(), new JSONArray());
      allLevels.put(CharacterBuilder.detail.SingleScores.toString(), singleLevels);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return allLevels;
  }
    }
    