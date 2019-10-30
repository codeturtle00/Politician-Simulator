package com.example.politicgame.data;

import android.content.Context;
import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.User.UserAccountManager;
import com.example.politicgame.User.UserAccount;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/** Class that handles authentication w/ login credentials and retrieves user information. */
public class LoginDataSource {
  private Context context;
  private FileSavingService fileSaving;
  private static final String FILE_NAME = "userLogin.json";

  public LoginDataSource(Context context) {
    this.context = context;
    this.fileSaving = new FileSavingService(context);
  }

  private boolean userAuthentication(String username, String password) {
    JSONArray jArray = fileSaving.readJsonFile(FILE_NAME);
    try {
      for (int i = 0; i < jArray.length(); i++) {
        if (jArray.getJSONObject(i).getString("UserName").equals(username)) {
          return jArray.getJSONObject(i).getString("Password").equals(password);
        }
      }
      return false;
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return false;
  }

  public Result<UserAccount> login(String username, String password) {
    try {
      if (this.userAuthentication(username, password)) {
        UserAccount user = new UserAccount(username,this.context);
        JSONArray userArray = this.fileSaving.readJsonFile(FILE_NAME);
        JSONObject userObjects = new JSONObject();
        for (int i= 0; i < userArray.length(); i ++ ){
          JSONObject userObject = userArray.getJSONObject(i);
          String key = userObject.keys().next();
          if (key.equals(username)){
            userObjects = userObject;
          }
        }
        UserAccountManager.loginUser = user;
        return new Result.Success<>(user);
      } else {
        return new Result.Error(new IOException("Error logging in"));
      }
    } catch (Exception e) {
      return new Result.Error(new IOException("Error logging in", e));
    }
  }

  public void logout() {
    // TODO: revoke authentication
  }
}
