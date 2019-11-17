package com.example.politicgame.UserActivity.RegisterActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.UserActivity.FormState;
import com.example.politicgame.UserActivity.LoginActivity.LoggedInActivity;
import com.example.politicgame.UserActivity.UserViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterViewModel extends UserViewModel {
  private MutableLiveData<FormState> registerFormState = new MutableLiveData<>();
  private Context context;
  private FileSavingService fileSaving;
  private PoliticGameApp app;
  private static final String FILE_NAME = "userLogin.json";

  LiveData<FormState> getRegisterFormState() {
    return registerFormState;
  }

  public RegisterViewModel(Context context) {
    this.context = context;
    this.fileSaving = this.fileSaving = new FileSavingService(context);
    Activity activity = (Activity) context;
    app = (PoliticGameApp) activity.getApplication();
  }

  public void registerDataValidate(String username, String password) {
    if (!isUserNameValid(username)) {
      registerFormState.setValue(new FormState(R.string.invalid_username, null));
    } else if (!isPasswordValid(password)) {
      registerFormState.setValue(new FormState(null, R.string.invalid_password));
    } else {
      registerFormState.setValue(new FormState(true));
    }
  }

  private boolean isDuplicate(String username) {
    JSONArray jArray = this.fileSaving.readJsonFile(FILE_NAME);
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

  public void saveUserToDb(String userName, String password) {
    Boolean saveSuccess = false;
    if (!userName.isEmpty() && !password.isEmpty()) {
      if (this.isDuplicate(userName)) {
        String duplicate = userName + " was found.Please select another name!";
        Toast.makeText(context.getApplicationContext(), duplicate, Toast.LENGTH_LONG).show();
      } else {
        JSONObject userObject = new JSONObject();
        saveSuccess = true;
        try {
          userObject.put("UserName", userName);
          userObject.put("Password", password);
        } catch (JSONException e) {
          e.printStackTrace();
        }
        fileSaving.appendJsonObject(userObject, FILE_NAME);
        UserAccount loginUser = new UserAccount(userName, context);
        String saved = "User" + userName + " is registered successfully!";
        Toast.makeText(context.getApplicationContext(), saved, Toast.LENGTH_LONG).show();
        // Sets current user
        if (saveSuccess) {
          app.setCurrentUser(loginUser);
          openLoggedInScreen();
        }
      }
    }
  }

  public void openLoggedInScreen() {
    Intent loggedIntent = new Intent(context, LoggedInActivity.class);
    context.startActivity(loggedIntent);
    Activity activity = (Activity) context;
    activity.finish();
  }
}
