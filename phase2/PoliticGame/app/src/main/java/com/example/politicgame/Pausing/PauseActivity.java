package com.example.politicgame.Pausing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.politicgame.Character.SpriteSetter;
import com.example.politicgame.GameActivity;
import com.example.politicgame.PopUpActivity;
import com.example.politicgame.R;
import com.example.politicgame.MainActivity;
import com.example.politicgame.Character.UserTools.UserAccount;

public class PauseActivity extends PopUpActivity {

  private final int DEFAULT_CODE = 0;
  private final int RESUME_CODE = 1;
  private final int QUIT_CODE = 2;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pause);

    setTitle("Paused");

    final ImageView pauseImage = findViewById(R.id.pause_menu_image);

    // Set the sprite for the pause menu
    SpriteSetter ss = new SpriteSetter(app);
    ss.setSprite(pauseImage);

    // Resume button
    final Button resumeB = findViewById(R.id.resume);
    resumeB.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            returnRequest(RESUME_CODE);
          }
        });

    // Quit to main menu button
    final Button loggedInB = findViewById(R.id.pause_main_menu);
    loggedInB.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            returnRequest(QUIT_CODE);
          }
        });
  }

  private void returnRequest(int requestCode) {
    Intent resultIntent = new Intent();
    resultIntent.putExtra("result", requestCode);

    setResult(RESULT_OK, resultIntent);
    finish();
  }
}
