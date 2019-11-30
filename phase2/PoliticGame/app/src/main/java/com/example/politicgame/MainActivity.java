package com.example.politicgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.politicgame.Games.BabyGame.BabyGameInstruction;
import com.example.politicgame.Leaderboard.LeaderBoardActivity;
import com.example.politicgame.LoadCharacter.LoadCharacterActivity;
import com.example.politicgame.UserActivity.LoginActivity.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends GameActivity {

  boolean theme;
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    theme = app.isThemeBlue();
    setContentView(R.layout.activity_main);

    ImageView trumpIMG = findViewById(R.id.trump);
    Animation animated_trump = AnimationUtils.loadAnimation(this, R.anim.animated_trump);
    trumpIMG.startAnimation(animated_trump);

    ImageView titleIMG = findViewById(R.id.gameTitle);
    Animation animated_title = AnimationUtils.loadAnimation(this, R.anim.animated_title);
    titleIMG.startAnimation(animated_title);

    //Generate Login button
    loginButton();

    //Generate Select Characters button
    selectCharacterButton();

    //Generate Leaderboard button
    leaderboardButton();

    // Settings button, opens the settings menu
    final ImageButton settingButton = findViewById(R.id.settings);
    settingButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            openSettings();
          }
        });
  }

  @Override
  protected void onResume() {
    super.onResume();
    // Reload buttons that depend on if user is logged in
    selectCharacterButton();
    loginButton();
    // If theme has changed, reload
    if (theme != app.isThemeBlue()) recreate();
  }

  private void leaderboardButton() {
    final Button boardButton = findViewById(R.id.leaderBoard);
    boardButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            openLeaderBoard();
          }
        });
  }

  private void selectCharacterButton() {
    final Button selectCharactersButton = findViewById(R.id.select_character);

    if (app.getCurrentUser() == null) {
      selectCharactersButton.setEnabled(false);
    }
    else {
      selectCharactersButton.setEnabled(true);
      selectCharactersButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
          openLoadCharacter();
        }
      });
    }
  }

  private void loginButton() {
    final Button loginButton = findViewById(R.id.login);

    if (app.getCurrentUser() == null) {
      loginButton.setText(getString(R.string.login));
      loginButton.setOnClickListener(
              new View.OnClickListener() {
                public void onClick(View v) {
                  // Code here executes on main thread after user presses button
                  openLoginPage();
                }
              });
    } else {
      loginButton.setText(getString(R.string.sign_out));
      loginButton.setOnClickListener(
              new View.OnClickListener() {
                public void onClick(View v) {
                  app.setCurrentUser(null);
                  // Reload necessary buttons
                  selectCharacterButton();
                  loginButton();
                }
              });
    }
  }

  public void openLoginPage() {
    /** Opens the login page */
    Intent loginPageIntent = new Intent(this, LoginActivity.class);
    startActivity(loginPageIntent);
    overridePendingTransition(R.anim.slide_up, R.anim.slide_up);
  }

  public void openLeaderBoard() {
    /** Opens the leaderboard screen */
    Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
    switchBoardIntent.putExtra("BoardType", "Election Mode");
    startActivity(switchBoardIntent);
    finish();
  }

  public void openSettings() {
    /** Open the settings menu */
    Intent settingsIntent = new Intent(this, SettingsActivity.class);
    startActivity(settingsIntent);
    overridePendingTransition(R.anim.slide_up, R.anim.slide_up);
  }

  public void openLoadCharacter () {
    Intent loadCharacters = new Intent(this, LoadCharacterActivity.class);
    startActivity(loadCharacters);
    finish();
  }
}
