package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.politicgame.BabyGame.BabyActivity;
import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.User.UserManager;
import com.example.politicgame.User.UserAccount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity {
  private FileSavingService fileSaving;
  private static final String FILE_NAME = "userLogin.json";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registration);
    final Button confirmation = findViewById(R.id.regitser);
    confirmation.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            openBabyGame();
          }
        });
    this.fileSaving = new FileSavingService(this);
  }

  public void openBabyGame() {
    Intent switchBabyIntent = new Intent(this, BabyActivity.class);
    startActivity(switchBabyIntent);
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

  public void saveUserToDb(View view) {
    final EditText usernameText = findViewById(R.id.username);
    final EditText passwordText = findViewById(R.id.password);
    String nameString = usernameText.getText().toString();
    String passString = passwordText.getText().toString();
    if (!nameString.isEmpty() && !passString.isEmpty()) {
      if (this.isDuplicate(nameString)) {
        String duplicate = nameString + " was found.Please select another name!";
        Toast.makeText(getApplicationContext(), duplicate, Toast.LENGTH_LONG).show();
      } else {
        JSONObject userObject = new JSONObject();
        try {
          userObject.put("UserName", nameString);
          userObject.put("Password", passString);
        } catch (JSONException e) {
          e.printStackTrace();
        }
        fileSaving.appendJsonObject(userObject, FILE_NAME);
        UserManager.loginUser = new UserAccount(nameString);
        String saved = "User" + nameString + " is registered successfully!";
        Toast.makeText(getApplicationContext(), saved, Toast.LENGTH_LONG).show();
      }
    }
  }
}
