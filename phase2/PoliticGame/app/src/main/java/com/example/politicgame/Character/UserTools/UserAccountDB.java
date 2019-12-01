package com.example.politicgame.Character.UserTools;

import android.content.Context;

import com.example.politicgame.Common.FileSavingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

class UserAccountDB {
  private String FILE_NAME;
  private String displayName;

  UserAccountDB(String FILE_NAME, String displayName) {
    this.FILE_NAME = FILE_NAME;
    this.displayName = displayName;
  }

  /**
   * Writes the changes of charArray to user.json
   *
   * @param context The context this method was originated from
   * @param fileSaving  The FileSavingService we are saving with
   * @param charArray   The JSONArray we are using to overwrite with
   */
  void saveToDb(Context context, FileSavingService fileSaving, JSONArray charArray) {
    if (new File(context.getFilesDir() + "/" + FILE_NAME).exists()) {
      //If the file already exists, write to it with this method
      saveExist(fileSaving, charArray);
    } else {
      //If the file does not exist, create and write to it with this method
      saveNotExist(fileSaving, charArray);
    }
  }

  /**
   * Overwrites the data for a specific character with charArray
   *
   * @param fileSaving  The FileSavingService we are saving with
   * @param charArray   The JSONArray we are using to overwrite with
   */
  private void saveExist(FileSavingService fileSaving, JSONArray charArray) {
    try {
      JSONObject userObject = new JSONObject();
      userObject.put(displayName, charArray);
      System.out.println("APPEND");
      System.out.println(userObject.toString());
      fileSaving.replaceJsonObject(userObject, FILE_NAME);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes the data for a specific character with charArray
   *
   * @param fileSaving  The FileSavingService we are saving with
   * @param charArray   The JSONArray we are using to write with
   */
  private void saveNotExist(FileSavingService fileSaving, JSONArray charArray) {
    try {
      JSONObject userObject = new JSONObject();
      JSONArray userArray = new JSONArray();
      userObject.put(displayName, charArray);
      userArray.put(userObject);
      System.out.println(userArray.toString());
      System.out.println("Write");
      fileSaving.writeJson(FILE_NAME, userArray);
    } catch (JSONException t) {
      t.printStackTrace();
    }
  }
}
