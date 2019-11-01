package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.Character.UserAccount;

import org.json.JSONException;
import org.json.JSONObject;

public class SummaryActivity extends GameActivity {

    final private TextView level1Result = findViewById(R.id.level_1_stats);
    final private TextView level2Result = findViewById(R.id.level_2_stats);
    final private TextView level3Result = findViewById(R.id.level_3_stats);
    final private TextView totalResult = findViewById(R.id.total_stats);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        fillInfoCell();

        final Button mainMenuButton = findViewById(R.id.summary_main_menu);
        mainMenuButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openLoggedIn();
                    }
                });
    }

    public void fillInfoCell(){
        UserAccount currentUser = app.getCurrentUser();
        JSONObject charInfo = currentUser.getCharByName(app.getCurrentCharacter());
        JSONObject level1;
        JSONObject level2;
        JSONObject level3;

        try{
            level1 = charInfo.getJSONObject("LEVEL1");
            level2 = charInfo.getJSONObject("LEVEL2");
            level3 = charInfo.getJSONObject("LEVEL3");

            int score1 = level1.getInt("score");
            int score2 = level2.getInt("score");
            int score3 = level3.getInt("score");

            level1Result.setText("Level 1 Score: " + score1);
            level2Result.setText("Level 2 Score: " + score2);
            level3Result.setText("Level 3 Score: " + score3);

            int totalScore = score1 + score2 + score3;

            totalResult.setText("Total score: " + totalScore);

            currentUser.addScore(app.getCurrentCharacter(), totalScore);
            currentUser.resetLevels(app.getCurrentCharacter());
            currentUser.saveToDb();

        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
