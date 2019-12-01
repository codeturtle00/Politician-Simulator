package com.example.politicgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.GameEnd.SummaryActivity;
import com.example.politicgame.Leaderboard.LeaderBoardActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * GameActivity includes code every minigame activity should have. To implement into a minigame's
 * activity, extend GameActivity instead of AppCompatActivity.
 */
public abstract class GameActivity extends AppCompatActivity {
  /** The application. */

  public static final int DEFAULT_CODE = 0;
  public static final int RESUME_CODE = 1;
  public static final int QUIT_TO_MENU_CODE = 2;
  public static final int REFRESH_BG = 3;
  protected PoliticGameApp app;

  protected void onStart() {
    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }

    super.onStart();
    System.out.println("The current theme is blue: " + app.isThemeBlue());
  }

  protected void onResume() {
    // If the theme is changed from the start menu then this will reflect that change
    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }

    super.onResume();
    System.out.println("The current theme is blue: " + app.isThemeBlue());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    app = (PoliticGameApp) getApplication();

    System.out.println("The current theme is blue: " + app.isThemeBlue());

    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }
  }

  /** Opens the main menu. */
  public void openMainMenu() {
    Intent mainMenuIntent = new Intent(this, MainActivity.class);
    startActivity(mainMenuIntent);
    finish();
  }

  /** Opens the summary page. */
  public void openSummary() {
    Intent summaryIntent = new Intent(this, SummaryActivity.class);
    startActivity(summaryIntent);
    finish();
  }

  /** Opens the leaderboard. */
  public void openLeaderBoard() {
    Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
    startActivityForResult(switchBoardIntent, 2);
  }

  /** Resumes or pauses the game. */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // requestCode refers to the request code parameter of openPauseMenu's startActivityForResult
    // call
    if (requestCode == 1) {
      if (resultCode == RESULT_OK) {
        int userInput = data.getIntExtra("result", 0);

        if (userInput == GameActivity.RESUME_CODE) {
          Log.i("onActivityResult", "User has decided to resume play");
        } else if (userInput == GameActivity.QUIT_TO_MENU_CODE) {
          Log.i("onActivityResult", "User has decided to return to menu");
          openMainMenu();
        }
      } else {
        Log.i("Result Code", "Result code is " + resultCode);
      }
    }
  }
}
