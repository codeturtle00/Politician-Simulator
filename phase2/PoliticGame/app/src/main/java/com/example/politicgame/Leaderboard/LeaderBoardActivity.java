package com.example.politicgame.Leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.GameActivity;
import com.example.politicgame.MainActivity;
import com.example.politicgame.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LeaderBoardActivity extends GameActivity implements AdapterView.OnItemSelectedListener {
  private final String MODEONE = "Election Mode";
  private final String MODETWO = "Baby Game";
  private final String MODETHREE = "Speech Game";
  private final String MODEFOUR = "Stamp Game";
  private final String FILE_NAME = "user.json";
  private FileSavingService fileSaving;

  private String boardType;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_leader_board);

    setTitle("Leaderboard");

    this.fileSaving = new FileSavingService(this);

    // Implementation for the Spinner
    boardType = getIntent().getStringExtra("BoardType");
    Spinner boardMenu = findViewById(R.id.election_spinner);
    boardMenu.setOnItemSelectedListener(this);

    // The drop down menu items
    List<String> boards = getSpinnerItem(boardType);

    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.board_spinner_layout, boards);

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Attaching data adapter to spinner
    boardMenu.setAdapter(dataAdapter);

    // Update the scoreboard
    updateBoard();

    // Return to main menu button
    final Button button = findViewById(R.id.back);
    button.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            returnMainMenu();
          }
        });
  }


  private List<String> getSpinnerItem(String currentBoard){
    List<String> boardsLeft = new ArrayList<>(Arrays.asList(MODEONE,MODETWO,MODETHREE,MODEFOUR));
    List<String> boards = new ArrayList<>();

    // Remove the current score board type
    boardsLeft.remove(currentBoard);

    // Add the board types in order of the current score board type and then the rest in normal order
    boards.add(currentBoard);
    boards.addAll(boardsLeft);

    return boards;
  }


  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    // On selecting a spinner item
    String item = parent.getItemAtPosition(position).toString();

    if(item != boardType){
      if (item.equals("Election Mode")){
        Log.i("ItemSelected", "Election mode leaderboard selected");
        boardType = MODEONE;
      } else if (item.equals("Baby Game")){
        Log.i("ItemSelected", "Baby game mode leaderboard selected");
        boardType = MODETWO;
      } else if (item.equals("Speech Game")){
        Log.i("ItemSelected", "Speech game mode leaderboard selected");
        boardType = MODETHREE;
      } else if (item.equals("Stamp Game")){
        Log.i("ItemSelected", "Stamp game mode leaderboard selected");
        boardType = MODEFOUR;
      }

      reloadBoard();
    }
  }


  public void onNothingSelected(AdapterView<?> parent) {
    Log.i("ItemSelected", "No drop down item has been selected");

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


  /** Return to the previous menu */
  private void returnMainMenu() {
    Intent backIntent = new Intent(this, MainActivity.class);
    startActivity(backIntent);
    finish();
  }


  /**
   * Reload the leaderboard
   */
  private void reloadBoard(){
    Intent restartIntent = new Intent(this, LeaderBoardActivity.class);
    restartIntent.putExtra("BoardType", boardType);
    startActivity(restartIntent);
    finish();
  }
}
