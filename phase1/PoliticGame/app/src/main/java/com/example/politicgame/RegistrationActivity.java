package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.politicgame.BabyGame.BabyActivity;
import com.example.politicgame.SpeechGame.SpeechInstructionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegistrationActivity extends AppCompatActivity {
  private static final String FILE_NAME = "userLogin.json";
  private static final String TAG = "Registration Activity";

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
  }

  public void openBabyGame() {
    Intent switchBabyIntent = new Intent(this, BabyActivity.class);
    startActivity(switchBabyIntent);
  }

  public boolean isDuplicate(String username) {
    String jsonString = new String();
    try {
      FileInputStream fileInputStream = openFileInput(FILE_NAME);
      System.out.println("File is empty?" + fileInputStream.toString());
      int size = fileInputStream.available();
      byte[] buffer = new byte[size];
      fileInputStream.read(buffer);
      fileInputStream.close();
      jsonString = new String(buffer, "UTF-8");
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      JSONArray jsonList = new JSONArray(jsonString);
      for (int i = 0; i < jsonList.length(); i++) {
        if (jsonList.getJSONObject(i).getString("UserName").equals(username)) {
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
      JSONObject userLogin = new JSONObject();
      JSONArray loginArray = new JSONArray();
      if (this.isDuplicate(nameString)) {
        String duplicate = nameString + "was found.Please select another name!";
        Toast.makeText(getApplicationContext(), duplicate, Toast.LENGTH_LONG).show();
      } else {
        String jsonString = new String();
        try {
          FileInputStream fileInputStream = openFileInput(FILE_NAME);
          System.out.println("File is empty?" + fileInputStream.toString());
          int size = fileInputStream.available();
          byte[] buffer = new byte[size];
          fileInputStream.read(buffer);
          fileInputStream.close();
          jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
          e.printStackTrace();
        }
        try {
          loginArray = new JSONArray(jsonString);
          userLogin.put("UserName", nameString);
          userLogin.put("Password", passString);
          loginArray.put(userLogin);
        } catch (JSONException e) {
          // throw an error
        }
        File rootDir = getFilesDir();
        Log.i(TAG, "root directory is " + rootDir);
        FileOutputStream outputStream;
        try {
          outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
          outputStream.write(loginArray.toString().getBytes());
          outputStream.close();
          System.out.println("Registered!");
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
