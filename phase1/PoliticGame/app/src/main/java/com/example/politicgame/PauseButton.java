package com.example.politicgame;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

/** Class for activities to add functional pause icon to their layouts
 *
 * If you want to add Pause button to your game, add this snippet to your activity's onCreate:
 * new PauseButton((ConstraintLayout) findViewByID(R.id.<id of your constraint layout>), this)
 *
 *
 * Explanation:
 *
 * (ConstraintLayout) type casts findViewByID
 * R.id.<id> is the ID of your layout. If you don't have one, insert android:id="@+id/ur id name"
 * to the top of your xml layout file. See Baby Game for example.
 * */

public class PauseButton {

  /**
   * Adds Pause Button to view
   *
   * @param view View to add pause button to
   * @param activity Activity for context to create new Intent
   */
  public PauseButton(ViewGroup view, Activity activity) {
    view.addView(generateButton(activity));
  }

  /** Generates functional Pause Button */
  private ImageButton generateButton(final Activity activity) {

    ImageButton pauseButton = new ImageButton(activity);

    // Sets the starting point of the button
    pauseButton.setX(40);
    pauseButton.setY(40);

    pauseButton.setImageResource(R.drawable.ic_pause_icon);
    ConstraintLayout.LayoutParams params =
        new ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    pauseButton.setLayoutParams(params);

    // This doesn't do anything. Need help setting parameters
      params.topMargin = 20;
      params.leftMargin = 20;

    pauseButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            Log.i("Button", "The pause button has been clicked");
            // The method below will pause the game and handle the following inputs
            openPauseMenu(activity);
          }
        });

    return pauseButton;
  }



  /** Function of Pause Button */
  private void openPauseMenu(Activity activity) {
    Intent pauseMenuIntent = new Intent(activity, PauseActivity.class);
    activity.startActivityForResult(pauseMenuIntent, 1);
  }
}
