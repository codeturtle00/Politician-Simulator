package com.example.politicgame.GamesActivity.StampGame;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.politicgame.Character.UserAccount;
import com.example.politicgame.GameActivity;
import com.example.politicgame.PauseButton;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;

public class StampActivity extends GameActivity {

  private final String LEVEL_NAME = "LEVEL3";
  StampGameHandler gh = new StampGameHandler();

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    app = (PoliticGameApp) getApplication();

    System.out.println("The current theme is blue: " + app.isThemeBlue());

    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stamp);

    setTitle("The Stamp Game");

    /*
     The Three TextView on screen that shows one's rating, the proposal, and number of
     proposal left
    */
    final TextView rating = findViewById(R.id.stamp_game_rating_score);
    final TextView promptDisplay = findViewById(R.id.npcPrompt);
    final TextView proposalLeft = findViewById(R.id.stamp_game_proposal_left);

    /*
    The yes button on screen, added corresponding methods so the score and prompt will change
    Upon clicking the yes button.
    */
    final Button button2 = findViewById(R.id.stamp_game_yes);
    button2.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            gh.changeRating(rating, true);
            gh.changeProposalNum(proposalLeft);
            gh.setPrompt(promptDisplay);
            if (gh.intFromTextView(rating) == 0
                || (gh.intFromTextView(rating) < 50 && gh.getPromptsSize(proposalLeft) == 0)) {
              openStampLost();
              //        finish();

            } else if (gh.intFromTextView(rating) == 100
                || (gh.intFromTextView(rating) >= 50 && gh.getPromptsSize(proposalLeft) == 0)) {
              openStampWon();
              //        finish();

            }
          }
        });

    /*
    The no button on screen, added corresponding methods so the score and prompt will change
    Upon clicking the no button.
    */
    final Button button3 = findViewById(R.id.stamp_game_no);
    button3.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            gh.changeRating(rating, false);
            gh.changeProposalNum(proposalLeft);
            gh.setPrompt(promptDisplay);
            if (gh.intFromTextView(rating) == 0
                || (gh.intFromTextView(rating) < 50 && gh.getPromptsSize(proposalLeft) == 0)) {
              openStampLost();
              //        finish();

            } else if (gh.intFromTextView(rating) == 100
                || (gh.intFromTextView(rating) >= 50 && gh.getPromptsSize(proposalLeft) == 0)) {
              openStampWon();
              //        finish();

            }
          }
        });

    new PauseButton((ConstraintLayout) findViewById(R.id.stampLayout), this);

    gh.changeProposalNum(proposalLeft);
    gh.setPrompt(promptDisplay);

    UserAccount currUser = app.getCurrentUser();
    int charId = currUser.getCharId(app.getCurrentCharacter());

    final ImageView pauseImage = findViewById(R.id.stamp_game_character_image);

    if (charId == 1){
      pauseImage.setImageResource(R.drawable.trump);
    } else if (charId == 2) {
      pauseImage.setImageResource(R.drawable.helmet_guy);
    } else {
      pauseImage.setImageResource(R.drawable.pause_filler);
    }
  }

  public void openStampLost() {
    Intent stampLostIntent = new Intent(this, StampActivityLost.class);
    saveGame(gh.getCurrentScore(), LEVEL_NAME);
    startActivity(stampLostIntent);
    finish();
  }

  public void openStampWon() {
    Intent stampWonIntent = new Intent(this, StampActivityWon.class);
    saveGame(gh.getCurrentScore(), LEVEL_NAME);
    startActivity(stampWonIntent);
    finish();
  }
}
