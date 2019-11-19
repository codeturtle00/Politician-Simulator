package com.example.politicgame.GameEnd;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.GameActivity;
import com.example.politicgame.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SummaryActivity extends GameActivity {

  private TextView level1Result;
  private TextView level2Result;
  private TextView level3Result;
  private TextView totalResult;
  private TextView finalText;
  private SaveInfo saveData;

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

    final Button saveMainMenuButton = findViewById(R.id.save_main_menu);
    saveMainMenuButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            saveData.saveInfo();
            openLoggedIn();
          }
        });

    final Button quitMainMenuButton = findViewById(R.id.quit_main_menu);
    quitMainMenuButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            saveData.resetLevels();
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
      JSONObject charObject = charInfo.getJSONObject(charName);

      level1 = charObject.getJSONObject("LEVEL1");
      level2 = charObject.getJSONObject("LEVEL2");
      level3 = charObject.getJSONObject("LEVEL3");

      int score1 = level1.getInt("score");
      int score2 = level2.getInt("score");
      int score3 = level3.getInt("score");
      int totalScore = score1 + score2 + score3;

      // Sets the data that will be saved if the user chooses to save
      saveData = new SaveInfo(currentUser, charName, totalScore);

      // Set the scores that you got for each level
      level1Result.setText("Level 1 Score: " + score1);
      level2Result.setText("Level 2 Score: " + score2);
      level3Result.setText("Level 3 Score: " + score3);

      totalResult.setText("Total score: " + totalScore);

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
