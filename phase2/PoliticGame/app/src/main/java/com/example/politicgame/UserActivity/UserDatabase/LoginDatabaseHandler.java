package com.example.politicgame.UserActivity.UserDatabase;

import android.app.Activity;
import android.content.Context;
import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.UserActivity.UserModel.DatabaseHandler;
import com.example.politicgame.UserActivity.UserModel.Result;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/** Handles credentials and retrieves user information. */
public class LoginDatabaseHandler implements DatabaseHandler {
  private Context context;
  private FileSavingService fileSaving;
  private PoliticGameApp app;

  public LoginDatabaseHandler(Context context) {
    this.context = context;
    this.fileSaving = new FileSavingService(context);
    Activity loginActivity = (Activity) context;
    this.app = (PoliticGameApp) loginActivity.getApplication();
  }

  private boolean userAuthentication(String username, String password) {
    JSONArray jArray = fileSaving.readJsonFile("userLogin.json");
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

  private boolean userFound(String username) {
    JSONArray jArray = fileSaving.readJsonFile("userLogin.json");
    try {
      for (int i = 0; i < jArray.length(); i++) {
        if (jArray.getJSONObject(i).getString("UserName").equals(username)) {
          return true;
        }
      }
      return false;
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return false;
  }

  private Result setAccountCharacter(String username) {
    UserAccount loginUser = new UserAccount(username, this.context);
    JSONArray jsonFile = fileSaving.readJsonFile("user.json");
    try {
      for (int i = 0; i < jsonFile.length(); i++) {
        String userFileName = jsonFile.getJSONObject(i).keys().next();
        if (userFileName.equals(username)) {
          loginUser.setCharArray(jsonFile.getJSONObject(i).getJSONArray(username));
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    app.setCurrentUser(loginUser);
    return new Result.Success(loginUser);
  }

  /**
   * Precondition: Username and password passed into this class is clean and valid as it go through
   * FormState
   */
  @Override
  public Result connectDatabase(String username, String password) {
    if (!this.userFound(username)) {
      return new Result.InvalidResult.EmptyResult(username);
    } else if (this.userAuthentication(username, password)) {
      return setAccountCharacter(username);
    } else {
      return new Result.Error(new IOException("Error logging in"));
    }
  }
}
