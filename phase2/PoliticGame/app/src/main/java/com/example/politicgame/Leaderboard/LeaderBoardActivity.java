package com.example.politicgame.Leaderboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.GameActivity;
import com.example.politicgame.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class LeaderBoardActivity extends GameActivity {

  private PoliticGameApp app;
  private final String FILE_NAME = "user.json";
  private FileSavingService fileSaving;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_leader_board);

    setTitle("Leaderboard");

    this.fileSaving = new FileSavingService(this);

    updateBoard();

    // Return to main menu button
    final Button button = findViewById(R.id.back);
    button.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            BackToMenu();
          }
        });
  }

  public void BackToMenu() {
    /** Returns to main menu */
    finish();
  }

  public void updateBoard() {
    /** Updates the leaderboard */
    // The JSONArray will come from Jacky's API for reading JSON
    JSONArray charScores = getBoard();

    JSONObject first = new JSONObject();
    JSONObject second = new JSONObject();
    JSONObject third = new JSONObject();
    try {
      first.put("filler", new JSONObject().put("score", 0));
      second.put("filler", new JSONObject().put("score", 0));
      third.put("filler", new JSONObject().put("score", 0));

      for (int i = 0; i < charScores.length(); i++) {
        JSONObject charInfo = charScores.getJSONObject(i);
        String charName = charInfo.keys().next();
        int charScoreNum = charInfo.getJSONObject(charName).getInt("score");

        Log.i("First", first.toString());
        Log.i("Second", second.toString());
        Log.i("Third", third.toString());
        Log.i("charScores(name)", charInfo.toString());

        if (charScoreNum > first.getJSONObject((first.keys().next())).getInt("score")) {
          third = second;
          second = first;
          first = new JSONObject().put(charName, charInfo.getJSONObject(charName));
        } else if (charScoreNum > second.getJSONObject((second.keys().next())).getInt("score")) {
          third = second;
          second = new JSONObject().put(charName, charInfo.getJSONObject(charName));
        } else if (charScoreNum > third.getJSONObject((third.keys().next())).getInt("score")) {
          third = new JSONObject().put(charName, charInfo.getJSONObject(charName));
        }
      }

      // The actual display of the leaderboard
      final TextView player1 = findViewById(R.id.player1);
      player1.setText(
          "First\nUser: "
              + first.getJSONObject(first.keys().next()).getString("userName")
              + "\nGameCharacter: "
              + first.keys().next()
              + "\nScore: "
              + first.getJSONObject(first.keys().next()).getInt("score"));

      final TextView player2 = findViewById(R.id.player2);
      player2.setText(
          "Second\nUser: "
              + second.getJSONObject(second.keys().next()).getString("userName")
              + "\nGameCharacter: "
              + second.keys().next()
              + "\nScore: "
              + second.getJSONObject(second.keys().next()).getInt("score"));

      final TextView player3 = findViewById(R.id.player3);
      player3.setText(
          "Third\nUser: "
              + third.getJSONObject(third.keys().next()).getString("userName")
              + "\nGameCharacter: "
              + third.keys().next()
              + "\nScore: "
              + third.getJSONObject(third.keys().next()).getInt("score"));

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public JSONArray getBoard() {
    /** Retrieves the leaderboard information */
    JSONArray jsonList = this.fileSaving.readJsonFile(FILE_NAME);

    JSONArray boardList = new JSONArray();

    try {
      // A JSON files containing the User, their characters and their scores
      for (int i = 0; i < jsonList.length(); i++) {
        Iterator<String> userKeys = jsonList.getJSONObject(i).keys();
        while (userKeys.hasNext()) {
          String userKey = userKeys.next(); // String version of the userName
          JSONArray charArray = jsonList.getJSONObject(i).getJSONArray(userKey);

          for (int j = 0; j < charArray.length(); j++) {
            JSONObject currentCharacter = charArray.getJSONObject(j);
            String charName = currentCharacter.keys().next();

            JSONArray scores = currentCharacter.getJSONObject(charName).getJSONArray("SCORE");

            for (int k = 0; k < scores.length(); k++) {
              JSONObject charScore = new JSONObject();
              JSONObject charInfo = new JSONObject();

              charInfo.put("userName", userKey);
              charInfo.put("score", scores.getInt(k));
              charScore.put(charName, charInfo);

              Log.i("Character Score", charScore.toString());

              boardList.put(charScore);
            }
          }
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    Log.i("Character Scores", boardList.toString());

    return boardList;
  }
}
