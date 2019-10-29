package com.example.politicgame.data;

import android.content.Context;
import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.LoginUserManager;
import com.example.politicgame.data.model.LoggedInUser;
import org.json.JSONArray;
import org.json.JSONException;
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

  public Result<LoggedInUser> login(String username, String password) {
    try {
      if (this.userAuthentication(username, password)) {
        LoggedInUser user = new LoggedInUser(java.util.UUID.randomUUID().toString(), username);
        LoginUserManager.loginUser = user;
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
