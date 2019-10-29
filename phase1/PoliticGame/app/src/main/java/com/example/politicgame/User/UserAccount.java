package com.example.politicgame.User;

import org.json.JSONArray;
import org.json.JSONObject;

/** Data class that captures user information for logged in users retrieved from LoginRepository */
public class UserAccount {
  private String displayName;
  private JSONArray charArray;
  private JSONObject currenChar;

  public UserAccount(String displayName) {
    this.displayName = displayName;
  }

  public void setCharArray(JSONArray charArray) {
    this.charArray = charArray;
  }

  public String getDisplayName() {
    return displayName;
  }
}
