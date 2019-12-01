package com.example.politicgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class SettingsActivity extends PopUpActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    setTitle("Settings");

    // Music player's current track
    final TextView currentTrack = findViewById(R.id.currentTrackText);
    currentTrack.setText("Track: " + app.getCurrentTrackNum());

    // Blue theme selector
    final RadioButton radioBlue = findViewById(R.id.colorBlue);
    if (app.isThemeBlue()) radioBlue.setChecked(true);
    radioBlue.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            app.chooseBlueTheme(true);
            returnRequest(GameActivity.REFRESH_BG);
          }
        });

    // Red theme selector
    final RadioButton radioRed = findViewById(R.id.colorRed);
    if (!app.isThemeBlue()) radioRed.setChecked(true);
    radioRed.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            app.chooseBlueTheme(false);
            returnRequest(GameActivity.REFRESH_BG);
          }
        });

    final ImageButton quitButton = findViewById(R.id.closeSettings);
    quitButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            finish();
          }
        });

    final Button changeMusicButton = findViewById(R.id.changeMusic);
    changeMusicButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            changeMusic();
            currentTrack.setText("Track: " + app.getCurrentTrackNum());
          }
        });

    final Button toggleMusicButton = findViewById(R.id.toggleMusic);
    toggleMusicButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            toggleMusic();
            if (!(app.isMusicOn())) {
              currentTrack.setText("Paused");
            } else {
              currentTrack.setText("Track: " + app.getCurrentTrackNum());
            }
          }
        });
  }

  /** Changes the song to the next track */
  public void changeMusic() {
    app.switchMusic();
  }

  /** Turns the music off or on based on its previous status */
  public void toggleMusic() {
    app.toggleMusic();
  }

  /** Restarts the activity to reflect theme changes */
  public void restart() {
    Intent restart = new Intent(this, SettingsActivity.class);
    startActivity(restart);
  }
}
