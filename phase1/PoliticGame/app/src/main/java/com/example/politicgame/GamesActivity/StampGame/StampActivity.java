package com.example.politicgame.GamesActivity.StampGame;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.GameActivity;
import com.example.politicgame.PauseButton;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;

public class StampActivity extends GameActivity {

    final private String LEVEL_NAME = "LEVEL3";
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

        final TextView rating = findViewById(R.id.stamp_game_rating_score);
        final TextView promptDisplay = findViewById(R.id.npcPrompt);
        final TextView proposalLeft = findViewById(R.id.stamp_game_proposal_left);


//        final Button button = findViewById(R.id.leaderBoard);
//        button.setOnClickListener(
//                new View.OnClickListener() {
//                    public void onClick(View v) {
//                        // Code here executes on main thread after user presses button
//                        openLeaderBoard();
//                    }
//                });

        final Button button2 = findViewById(R.id.stamp_game_yes);
        button2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        gh.changeRating(rating, true);
                        gh.changeProposalNum(proposalLeft);
                        gh.setPrompt(promptDisplay);
                        if (gh.intFromTextView(rating) == 0 || (gh.intFromTextView(rating) < 80 && gh.getPromptsSize(proposalLeft) == 0)) {
                            openStampLost();
                            //        finish();

                        } else if (gh.intFromTextView(rating) == 100 || (gh.intFromTextView(rating) >= 80 && gh.getPromptsSize(proposalLeft) == 0)) {
                            openStampWon();
                            //        finish();

                        }

                    }
                });

        final Button button3 = findViewById(R.id.stamp_game_no);
        button3.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        gh.changeRating(rating, false);
                        gh.changeProposalNum(proposalLeft);
                        gh.setPrompt(promptDisplay);
                        if (gh.intFromTextView(rating) == 0 || (gh.intFromTextView(rating) < 80 && gh.getPromptsSize(proposalLeft) == 0)) {
                            openStampLost();
                            //        finish();

                        } else if (gh.intFromTextView(rating) == 100 || (gh.intFromTextView(rating) >= 80 && gh.getPromptsSize(proposalLeft) == 0)) {
                            openStampWon();
                            //        finish();

                        }
                    }
                });


        new PauseButton((ConstraintLayout) findViewById(R.id.stampLayout), this);

        gh.changeProposalNum(proposalLeft);
        gh.setPrompt(promptDisplay);
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