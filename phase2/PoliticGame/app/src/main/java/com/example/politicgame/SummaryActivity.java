package com.example.politicgame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.Character.UserTools.UserAccount;

import org.json.JSONException;
import org.json.JSONObject;

public class SummaryActivity extends GameActivity {

  private TextView level1Result;
  private TextView level2Result;
  private TextView level3Result;
  private TextView totalResult;
  private TextView finalText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_summary);

    level1Result = findViewById(R.id.level_1_stats);
    level2Result = findViewById(R.id.level_2_stats);
    level3Result = findViewById(R.id.level_3_stats);
    totalResult = findViewById(R.id.total_stats);
    finalText = findViewById(R.id.end_text);

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

  /** Fills all the TextViews with the results of each level */
  public void fillInfoCell() {
    String charName = app.getCurrentCharacter();

    UserAccount currentUser = app.getCurrentUser();
    JSONObject charInfo = currentUser.getCharByName(charName);
    JSONObject level1;
    JSONObject level2;
    JSONObject level3;

    Log.i("Current Character Stats", charInfo.toString());

    try {
      level1 = charInfo.getJSONObject(charName).getJSONObject("LEVEL1");
      level2 = charInfo.getJSONObject(charName).getJSONObject("LEVEL2");
      level3 = charInfo.getJSONObject(charName).getJSONObject("LEVEL3");

      int score1 = level1.getInt("score");
      int score2 = level2.getInt("score");
      int score3 = level3.getInt("score");

      level1Result.setText("Level 1 Score: " + score1);
      level2Result.setText("Level 2 Score: " + score2);
      level3Result.setText("Level 3 Score: " + score3);

      int totalScore = score1 + score2 + score3;

      totalResult.setText("Total score: " + totalScore);

      currentUser.addScore(charName, totalScore);
      currentUser.resetLevels(charName);
      currentUser.saveToDb();

      if (totalScore >= 200) {
        finalText.setText("Congratulations, you have won the election. With " + totalScore + " points, you have beaten out your candidates.");
      } else {
        finalText.setText("Unfortunately, you have not gained enough support. With " + totalScore + " points, you need at least " + (200 - totalScore) + " more points to win. Try again next time!");
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
