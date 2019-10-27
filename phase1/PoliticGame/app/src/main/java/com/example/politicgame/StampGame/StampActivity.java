package com.example.politicgame.StampGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.LeaderBoardActivity;
import com.example.politicgame.R;

public class StampActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        StampGameHandler gh = new StampGameHandler();
        gh.createPrompt();
        String npcProposal = gh.getCurrentPrompt().getString();
        TextView promptDisplay = findViewById(R.id.npcPrompt);
        promptDisplay.setText(npcProposal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);


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
                        TextView rating = findViewById(R.id.stamp_game_rating_score);
                        changeScore(rating);
                    }
                });

        final Button button3 = findViewById(R.id.stamp_game_no);
        button3.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        TextView rating = findViewById(R.id.stamp_game_rating_score);
                        changeScore(rating);
                    }
                });
    }

    public void changeScore(TextView rating){
        String oldRating = rating.getText().toString();
        Integer score = Integer.valueOf(oldRating.substring(0, oldRating.length() -1));
        if (score != 0){
            Integer newScore = score - 5;
            String newRating = newScore.toString() + '%';
            rating.setText(newRating);
        }

    }

    public void openLeaderBoard() {
        Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
        startActivity(switchBoardIntent);
    }
}