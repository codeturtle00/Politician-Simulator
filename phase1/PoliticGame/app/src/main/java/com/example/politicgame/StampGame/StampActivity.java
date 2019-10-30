package com.example.politicgame.StampGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.LeaderBoardActivity;
import com.example.politicgame.PauseActivity;
import com.example.politicgame.MainActivity;
import com.example.politicgame.PauseButton;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;

public class StampActivity extends AppCompatActivity {

    private PoliticGameApp app;
    StampGameHandler gh = new StampGameHandler();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);
        final TextView rating = findViewById(R.id.stamp_game_rating_score);
        final TextView promptDisplay = findViewById(R.id.npcPrompt);
        final TextView proposalLeft = findViewById(R.id.stamp_game_proposal_left);


        final Button button = findViewById(R.id.leaderBoard);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openLeaderBoard();
                    }
                });

        final Button button2 = findViewById(R.id.stamp_game_yes);
        button2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        gh.changeRating(rating, true);
                        gh.changeProposalNum(proposalLeft);
                        gh.setPrompt(promptDisplay);
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
                    }
                });


        new PauseButton((ConstraintLayout) findViewById(R.id.stampLayout), this);

        gh.changeProposalNum(proposalLeft);
        gh.setPrompt(promptDisplay);
    }


    private void openLeaderBoard() {
        Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
        startActivity(switchBoardIntent);
    }

    public void openMainMenu() {
        Intent mainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(mainMenuIntent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //requestCode refers to the request code parameter of openPauseMenu's startActivityForResult call
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int userInput = data.getIntExtra("result", 0);

                if (userInput == 1) {
                    Log.i("Pause Result", "User has decided to resume play");
                } else if (userInput == 2) {
                    Log.i("Pause Result", "User has decided to quit the game");
                    openMainMenu();
                }
            } else {
                Log.i("Result Code", "Result code is " + resultCode);
            }
        }
    }
}