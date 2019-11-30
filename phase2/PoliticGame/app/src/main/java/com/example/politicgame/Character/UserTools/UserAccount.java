package com.example.politicgame.Character.UserTools;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.politicgame.Character.GameCharacter;
import com.example.politicgame.Common.FileSavingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository.
 * This class acts as a facade for UserAccountAddScore, UserAccountChar, UserAccountDB, and
 * UserAccountResetLevels. The purpose of this facade is to delegate specific tasks to different
 * classes, as we wish to maintain the single responsibility principle.
 */
public class UserAccount {
  private FileSavingService fileSaving;
  private static final String FILE_NAME = "user.json";
  private Context context;

  private UserAccountChar userAccountChar;
  private UserAccountDB userAccountDB;
  private UserAccountResetLevels userAccountResetLevels;
  private UserAccountAddScore userAccountAddScore;

  public UserAccount(String displayName, Context context) {
    this.context = context;
    this.fileSaving = new FileSavingService(context);
    this.userAccountChar = new UserAccountChar(displayName);
    this.userAccountDB = new UserAccountDB(FILE_NAME, displayName);
    this.userAccountResetLevels = new UserAccountResetLevels();
    this.userAccountAddScore = new UserAccountAddScore();
  }

  /**
   * Sets the game character to currentCharacter.
   *
   * @param currentCharacter the character to be set
   */
  public void setCurrentCharacter(GameCharacter currentCharacter) {
    userAccountChar.setCurrentCharacter(currentCharacter);
  }

  /**
   * Returns this game's character.
   *
   * @return the character to be returned
   */
  public GameCharacter getCurrentCharacter() {
    return userAccountChar.getCurrentCharacter();
  }

  /**
   * Returns the ID of the character
   *
   * @param charName the name of the character
   * @return the ID of the character
   */
  public int getCharId(String charName) {
    return userAccountChar.getCharId(charName);
  }

  /**
   * Checks if the user already has a character with the same name
   *
   * @param charName the name of the character
   * @return If the user has another character with the same name
   */
  public boolean isDuplicate(String charName) {
    return userAccountChar.isDuplicate(charName);
  }

  /**
   * Adds charObject to charArray.
   *
   * @param charObject The object added to charArray, it should contain level info and score
   */
  public void addCharArray(JSONObject charObject) {
    userAccountChar.addCharArray(charObject);
  }

  /**
   * Sets charArray to charObject.
   *
   * @param charArray A JSONArray that will replace the old charArray
   */
  public void setCharArray(JSONArray charArray) {
    userAccountChar.setCharArray(charArray);
  }

  /**
   * Returns charArray
   *
   * @return the JSONArray of the users characters
   */
  public JSONArray getCharArray() {
    return userAccountChar.getCharArray();
  }

  /**
   * Returns the display name of the current user.
   *
   * @return User name of the currently logged in user
   */
  public String getDisplayName() {
    return userAccountChar.getDisplayName();
  }

  /**
   * Deleted character from charArray by finding their name. Note that this method requires at least
   * a minSdkVersion of 19 in order to use JSONArray.remove()
   *
   * @param charName The name of the character
   */
  public void deleteCharByName(String charName) {
    userAccountChar.deleteCharByName(charName);
  }

  /**
   * Reset the level information of the character given.
   *
   * @param charName The character name who will have their information erased
   */
  public void resetLevels(String charName) {
    JSONArray charArray = getCharArray();
    userAccountResetLevels.resetLevels(charArray, charName);
  }

  /**
   * Returns the JSONObject with the charName key in charArray.
   *
   * @param charName The name of the character who's information we want to retrieve
   * @return The JSONObject of the character
   */
  public JSONObject getCharByName(String charName) {
    return userAccountChar.getCharByName(charName);
  }

  /**
   * Adds the score of a new playthrough to the characters' score history.
   *
   * @param charName The name of the character who's score we are updating
   * @param score The new score to be added to the JSONArray
   */
  public void addScore(String charName, int score) {
    JSONArray charArray = getCharArray();
    userAccountAddScore.addScore(charArray, charName, score);
  }


  /**
   * Saves a high score for an individual level, used for the individial game modes
   *
   * @param levelName   The name of the level's key. Possible values: "LEVEL1", "LEVEL2", "LEVEL3"
   * @param charName    The name of the character to write to
   * @param score       The score that the character has achieved
   */
  public void singleSave (String levelName, String charName, int score){
    JSONArray charArray = getCharArray();

    try {
      for (int i = 0; i < charArray.length(); i++) {
        JSONObject currentChar = charArray.getJSONObject(i);
        String currName = currentChar.keys().next();
        if (currName.equals(charName)) {
          currentChar.getJSONObject(charName).getJSONObject("SingleScores").getJSONArray(levelName).put(score);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the String version of charArray.
   *
   * @return A String version of the charArray
   */
  @Override
  @NonNull
  public String toString() {
    StringBuilder newString = new StringBuilder();
    String displayName = getDisplayName();
    newString.append(displayName + "/n");
    newString.append(getCharArray().toString());
    return newString.toString();
  }


  /**
   * Saves the current version of charArray to user.json and will create the file if it does not
   * already exist.
   */
  public void saveToDb() {
    System.out.println();
    JSONArray charArray = getCharArray();
    userAccountDB.saveToDb(this.context, this.fileSaving, charArray);
  }
}
