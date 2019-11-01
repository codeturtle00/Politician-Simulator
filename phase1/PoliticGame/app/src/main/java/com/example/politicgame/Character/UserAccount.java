package com.example.politicgame.Character;

import android.content.Context;

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
  private JSONObject currentChar;
  private Context context;

  public UserAccount(String displayName, Context context) {
    this.context = context;
    this.fileSaving = new FileSavingService(context);
    this.displayName = displayName;
  }

  public void addCharArray(JSONObject charObject) {
    this.charArray.put(charObject);
  }

  public void setCharArray(JSONArray charObject) {
    this.charArray = charObject;
  }

  public JSONArray getCharArray(){return this.charArray;}

  public String getDisplayName() {
    return displayName;
  }

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

  public void resetLevels(String charName){
    try{
      for(int i = 0; i < charArray.length(); i++){
        JSONObject currentChar = charArray.getJSONObject(i);
        String currName = currentChar.keys().next();
        if (currName.equals(charName)){
          JSONObject characterInfo = currentChar.getJSONObject(charName);

          JSONObject level1 = characterInfo.getJSONObject("LEVEL1");
          level1 = new JSONObject().put("rating", level1.getInt("rating"));
          JSONObject level2 = characterInfo.getJSONObject("LEVEL2");
          level2 = new JSONObject().put("rating", level2.getInt("rating"));
          JSONObject level3 = characterInfo.getJSONObject("LEVEL3");
          level3 = new JSONObject().put("rating", level3.getInt("rating"));
        }

      }
    } catch (JSONException e){
      e.printStackTrace();
    }
  }

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

  @Override
  @NonNull
  public String toString(){
    StringBuilder newString = new StringBuilder();
    newString.append(this.displayName + "/n");
    newString.append(charArray.toString());
    return newString.toString();
  }

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