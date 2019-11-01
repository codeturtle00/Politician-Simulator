package com.example.politicgame.User;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.politicgame.Common.FileSavingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/** Data class that captures user information for logged in users
 * retrieved from LoginRepository */
public class UserAccount {
  private FileSavingService fileSaving;
  private static final String FILE_NAME = "user.json";
  private String displayName;
  private JSONArray charArray = new JSONArray();
  private JSONObject currenChar;
  private Context context;

  public UserAccount(String displayName, Context context) {
    this.context = context;
    this.fileSaving = new FileSavingService(context);
    this.displayName = displayName;
  }

  public void setCharArray(JSONObject charObject) {
    this.charArray.put(charObject);
  }

  public String getDisplayName() {
    return displayName;
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