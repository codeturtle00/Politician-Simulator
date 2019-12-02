package com.example.politicgame.Games.StampGame;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.politicgame.Character.SpriteSetter;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.GameActivity;
import com.example.politicgame.Pausing.PauseButton;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;

public class StampActivity extends GameActivity {

  private final String LEVEL_NAME = "LEVEL3";
  private StampGameHandler gameHandler;

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    app = (PoliticGameApp) getApplication();
    gameHandler = new StampGameHandler(this);

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stamp);

    setTitle("The Stamp Game");

    /*
     The Three TextView on screen that shows one's rating, the proposal, and number of
     proposal arrow_left
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
            gameHandler.updateCurrentScore(true);
            if (gameHandler.checkGameWon() == 1) {
              openStampWon();
            } else if (gameHandler.checkGameWon() == 0) {
              openStampLost();
            }
            gameHandler.changeDisplay(proposalLeft, rating, promptDisplay);
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
            gameHandler.updateCurrentScore(false);
            if (gameHandler.checkGameWon() == 1) {
              openStampWon();
            } else if (gameHandler.checkGameWon() == 0) {
              openStampLost();
            }
            gameHandler.changeDisplay(proposalLeft, rating, promptDisplay);
          }
        });

    new PauseButton((ConstraintLayout) findViewById(R.id.stampLayout), this);

    gameHandler.displayCurrentPrompt(promptDisplay);
    gameHandler.displayCurrentScore(rating);
    gameHandler.displayProposalLeft(proposalLeft);

    // Set the sprite for the game menu
    final ImageView pauseImage = findViewById(R.id.stamp_game_character_image);
    SpriteSetter ss = new SpriteSetter(app);
    ss.setSprite(pauseImage);
  }

  public void openStampLost() {
    Intent stampLostIntent = new Intent(this, StampActivityLost.class);
    stampLostIntent.putExtra("GameMode", getIntent().getSerializableExtra("GameMode"));
    stampLostIntent.putExtra("score", gameHandler.getCurrentScore());
    // saveGame(gameHandler.getCurrentScore(), LEVEL_NAME);
    startActivity(stampLostIntent);
    finish();
  }

  public void openStampWon() {
    Intent stampWonIntent = new Intent(this, StampActivityWon.class);
    stampWonIntent.putExtra("GameMode", getIntent().getSerializableExtra("GameMode"));
    stampWonIntent.putExtra("score", gameHandler.getCurrentScore());
    // saveGame(gameHandler.getCurrentScore(), LEVEL_NAME);
    startActivity(stampWonIntent);
    finish();
  }
}
