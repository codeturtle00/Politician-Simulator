package com.example.politicgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.politicgame.Leaderboard.LeaderBoardActivity;
import com.example.politicgame.LoadCharacter.LoadCharacterActivity;
import com.example.politicgame.UserActivity.LoginActivity.LoginActivity;

public class MainActivity extends GameActivity {

  boolean theme;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    theme = app.isThemeBlue();
    setContentView(R.layout.activity_main);

    // Animate Trump
    ImageView trumpIMG = findViewById(R.id.trump);
    Animation animated_trump = AnimationUtils.loadAnimation(this, R.anim.animated_trump);
    trumpIMG.startAnimation(animated_trump);

    // Animate Title
    ImageView titleIMG = findViewById(R.id.gameTitle);
    Animation animated_title = AnimationUtils.loadAnimation(this, R.anim.animated_title);
    titleIMG.startAnimation(animated_title);

    generateLoginButton((TextView) findViewById(R.id.login));
    generateSelectCharacterButton(findViewById(R.id.play));
    generateLeaderboardButton(findViewById(R.id.leaderBoard));
    generateSettingsButton(findViewById(R.id.settings));
  }

  @Override
  protected void onResume() {
    super.onResume();
    // Reload buttons that depend on if user is logged in
    generateLoginButton((TextView) findViewById(R.id.login));
    generateSelectCharacterButton(findViewById(R.id.play));
  }

  private void generateLeaderboardButton(View boardButton) {
    boardButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            openLeaderBoard();
          }
        });
  }

  private void generateSelectCharacterButton(View selectCharactersButton) {
    if (app.getCurrentUser() == null) {
      selectCharactersButton.setEnabled(false);
    } else {
      selectCharactersButton.setEnabled(true);
      selectCharactersButton.setOnClickListener(
          new View.OnClickListener() {
            public void onClick(View v) {
              openLoadCharacter();
            }
          });
    }
  }

  private void generateLoginButton(TextView loginButton) {

    // Login Button changes to Sign Out if already logged in
    if (app.getCurrentUser() == null) {
      loginButton.setText(getString(R.string.login));
      loginButton.setOnClickListener(
          new View.OnClickListener() {
            public void onClick(View v) {
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
              generateLoginButton((TextView) findViewById(R.id.login));
              generateSelectCharacterButton(findViewById(R.id.play));
            }
          });
    }
  }

  private void generateSettingsButton(View settingButton) {
    settingButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            openSettings();
          }
        });
  }

  public void openLoginPage() {
    Intent loginPageIntent = new Intent(this, LoginActivity.class);
    startActivity(loginPageIntent);
    overridePendingTransition(R.anim.slide_up, 0);
  }

  public void openLeaderBoard() {
    Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
    switchBoardIntent.putExtra("BoardType", "Election Mode");
    startActivity(switchBoardIntent);
    finish();
  }

  public void openSettings() {
    Intent pauseMenuIntent = new Intent(this, SettingsActivity.class);
    startActivityForResult(pauseMenuIntent, 2);
  }

  public void openLoadCharacter() {
    Intent loadCharacters = new Intent(this, LoadCharacterActivity.class);
    startActivity(loadCharacters);
  }

  /**
   * Add on to GameActivity's onActivityResult. We add requestCode 2 for settings
   * to tell Main Menu user has changed themes.
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 2) {
      if (resultCode == RESULT_OK) {
        int userInput = data.getIntExtra("result", 0);

        if (userInput == GameActivity.REFRESH_BG) {
          Log.i("onActivityResult", "Refreshing background activity");
          overridePendingTransition(0, 0);
          recreate();
          overridePendingTransition(0, 0);
          openSettings();
        }
      } else {
        Log.i("Result Code", "Result code is " + resultCode);
      }
    }
  }
}
