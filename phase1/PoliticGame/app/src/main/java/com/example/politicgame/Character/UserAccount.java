package com.example.politicgame.Character;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.politicgame.Common.FileSavingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/** Data class that captures user information for logged in users
 * retrieved from LoginRepository */
public class UserAccount {
  private FileSavingService fileSaving;
  private static final String FILE_NAME = "user.json";
  private String displayName;
  private JSONArray charArray = new JSONArray();
  private Context context;

  public void setCurrentCharacter(GameCharacter currentCharacter) {
    this.currentCharacter = currentCharacter;
  }

  public GameCharacter getCurrentCharacter() {
    return currentCharacter;
  }

  private GameCharacter currentCharacter;

  public UserAccount(String displayName, Context context) {
    this.context = context;
    this.fileSaving = new FileSavingService(context);
    this.displayName = displayName;

  }

  public int getCharId(String charName){
    try{
    for (int i = 0; i < charArray.length(); i++){
      JSONObject charInfo = charArray.getJSONObject(i);
      String currName = charInfo.keys().next();

    }
    } catch (JSONException e){
      e.printStackTrace();
    }
    return 1;
  }

  /**
   * Adds charObject to charArray
   *
   * @param charObject  The object added to charArray, it should contain level info and score
   */
  public void addCharArray(JSONObject charObject) {
    this.charArray.put(charObject);
  }

  /**
   * Sets charArray to charObject
   *
   * @param charArray A JSONArray that will replace the old charArray
   */
  public void setCharArray(JSONArray charArray) {
    this.charArray = charArray;
  }


  /**
   * Returns charArray
   *
   * @return the JSONArray of the users characters
   */
  public JSONArray getCharArray(){return this.charArray;}


  /**
   * Returns the display name of the current user
   *
   * @return User name of the currently logged in user
   */
  public String getDisplayName() {
    return displayName;
  }


  /**
   * Deleted character from charArray by finding their name. Note that this method requires at least
   * a minSdkVersion of 19 in order to use JSONArray.remove()
   *
   * @param charName  The name of the character
   */
  public void deleteCharByName(String charName){
    try{
      for(int i = 0; i < charArray.length(); i++){
        JSONObject currentChar = charArray.getJSONObject(i);
        String currName = currentChar.keys().next();
        if (currName.equals(charName)){
          charArray.remove(i);
        }

      }
    } catch (JSONException e){
      e.printStackTrace();
    }
  }


  /**
   * Reset the level information of the character given
   *
   * @param charName  The character name who will have their information erased
   */
  public void resetLevels(String charName){
    try{
      for(int i = 0; i < charArray.length(); i++){
        JSONObject currentChar = charArray.getJSONObject(i);
        String currName = currentChar.keys().next();
        if (currName.equals(charName)){
          JSONObject characterInfo = currentChar.getJSONObject(charName);

          JSONObject level1 = characterInfo.getJSONObject("LEVEL1");
          Log.i("Level 1", level1.toString());
          level1.remove("score");
          level1.put("complete", false);

          JSONObject level2 = characterInfo.getJSONObject("LEVEL2");
          Log.i("Level 2", level2.toString());
          level2.remove("score");
          level2.put("complete", false);

          JSONObject level3 = characterInfo.getJSONObject("LEVEL3");
          Log.i("Level 3", level3.toString());
          level3.remove("score");
          level3.put("complete", false);

          Log.i("Character Info", characterInfo.toString());
        }

      }
    } catch (JSONException e){
      e.printStackTrace();
    }
  }


  /**
   * Returns the JSONObject with the charName key in charArray
   *
   * @param charName  The name of the character who's information we want to retrieve
   * @return The JSONObject of the character
   */
  public JSONObject getCharByName(String charName){
    try{
      for(int i = 0; i < charArray.length(); i++){
        JSONObject currentChar = charArray.getJSONObject(i);
        String currName = currentChar.keys().next();
        if (currName.equals(charName)){
          return currentChar;
        }
      }
    } catch (JSONException e){
      e.printStackTrace();
    }

    return new JSONObject();
  }


  /**
   * Adds the score of a new playthrough to the characters' score history
   *
   * @param charName  The name of the character who's score we are updating
   * @param score     The new score to be added to the JSONArray
   */
  public void addScore(String charName, int score){
    try{
      for(int i = 0; i < charArray.length(); i++){
        JSONObject currentChar = charArray.getJSONObject(i);
        String currName = currentChar.keys().next();
        if (currName.equals(charName)){
          currentChar.getJSONObject(charName).getJSONArray("SCORE").put(score);
        }
      }
    } catch (JSONException e){
      e.printStackTrace();
    }

  }

  /**
   * Returns the String version of charArray
   *
   * @return  A String version of the charArray
   */
  @Override
  @NonNull
  public String toString(){
    StringBuilder newString = new StringBuilder();
    newString.append(this.displayName + "/n");
    newString.append(charArray.toString());
    return newString.toString();
  }


  /**
   * Saves the current version of charArray to user.json and will create the file if it does not
   * already exist
   */
  public void saveToDb(){System.out.println();
    if (new File(context.getFilesDir() +"/" + FILE_NAME).exists()){
    try{
    JSONObject userObject = new JSONObject();
    userObject.put(this.displayName, this.charArray);
    System.out.println("APPEND");
    System.out.println(userObject.toString());
    this.fileSaving.replaceJsonObject(userObject, FILE_NAME);
    }
    catch(Exception e){
      e.printStackTrace();}
    }
    else{
      try{
      JSONObject userObject = new JSONObject();
      JSONArray userArray = new JSONArray();
      userObject.put(this.displayName, this.charArray);
      userArray.put(userObject);
      System.out.println(userArray.toString());
      System.out.println("Write");
      this.fileSaving.writeJson(FILE_NAME,userArray);
      }
      catch (JSONException t){
        t.printStackTrace();
      }
    }
  }
  }