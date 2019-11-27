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

public class LeaderBoardActivity extends GameActivity
    implements AdapterView.OnItemSelectedListener {
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
    ArrayAdapter<String> dataAdapter =
        new ArrayAdapter<String>(this, R.layout.board_spinner_layout, boards);

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Attaching data adapter to spinner
    boardMenu.setAdapter(dataAdapter);

    // Update the scoreboard
    updateBoard(boardType);

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

  private List<String> getSpinnerItem(String currentBoard) {
    List<String> boardsLeft = new ArrayList<>(Arrays.asList(MODEONE, MODETWO, MODETHREE, MODEFOUR));
    List<String> boards = new ArrayList<>();

    // Remove the current score board type
    boardsLeft.remove(currentBoard);

    // Add the board types in order of the current score board type and then the rest in normal
    // order
    boards.add(currentBoard);
    boards.addAll(boardsLeft);

    return boards;
  }

  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    // On selecting a spinner item
    String item = parent.getItemAtPosition(position).toString();

    if (item != boardType) {
      if (item.equals(MODEONE)) {
        Log.i("ItemSelected", "Election mode leaderboard selected");
        boardType = MODEONE;
      } else if (item.equals(MODETWO)) {
        Log.i("ItemSelected", "Baby game mode leaderboard selected");
        boardType = MODETWO;
      } else if (item.equals(MODETHREE)) {
        Log.i("ItemSelected", "Speech game mode leaderboard selected");
        boardType = MODETHREE;
      } else if (item.equals(MODEFOUR)) {
        Log.i("ItemSelected", "Stamp game mode leaderboard selected");
        boardType = MODEFOUR;
      }

      reloadBoard();
    }
  }

  public void onNothingSelected(AdapterView<?> parent) {
    Log.i("ItemSelected", "No drop down item has been selected");
  }

  public void updateBoard(String boardType) {
    LeaderBoard board = getLeaderBoard(boardType);
    List<JSONObject> boardCell = board.getBoard();

    JSONObject first = boardCell.get(0);
    JSONObject second = boardCell.get(1);
    JSONObject third = boardCell.get(2);

    // The actual display of the leaderboard
    final TextView player1 = findViewById(R.id.player1);
    applyScores(player1, first, "First");

    final TextView player2 = findViewById(R.id.player2);
    applyScores(player2, second, "Second");

    final TextView player3 = findViewById(R.id.player3);
    applyScores(player3, third, "Third");
  }

  private void applyScores(TextView textField, JSONObject scoreInfo, String rank){
    try{
    if (!scoreInfo.has("fill")) {
      textField.setText(
              rank + "\nUser: "
                      + scoreInfo.getJSONObject(scoreInfo.keys().next()).getString("userName")
                      + "\nGameCharacter: "
                      + scoreInfo.keys().next()
                      + "\nScore: "
                      + scoreInfo.getJSONObject(scoreInfo.keys().next()).getInt("score"));
    }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private LeaderBoard getLeaderBoard(String boardType) {
    LeaderBoard lb;

    if (boardType.equals(MODEONE)) {
      Log.i("ItemSelected", "Election mode leaderboard selected");
      lb = new ElectionBoardGetter(this);
    } else if (boardType.equals(MODETWO)) {
      Log.i("ItemSelected", "Baby game mode leaderboard selected");
      lb = new SingleBoardGetter(this, "LEVEL1");
    } else if (boardType.equals(MODETHREE)) {
      Log.i("ItemSelected", "Speech game mode leaderboard selected");
      lb = new SingleBoardGetter(this, "LEVEL2");
    } else if (boardType.equals(MODEFOUR)) {
      Log.i("ItemSelected", "Stamp game mode leaderboard selected");
      lb = new SingleBoardGetter(this, "LEVEL3");
    } else {
      lb = new ElectionBoardGetter(this);
    }

    return lb;
  }

  /** Return to the previous menu */
  private void returnMainMenu() {
    Intent backIntent = new Intent(this, MainActivity.class);
    startActivity(backIntent);
    finish();
  }

  /** Reload the leaderboard */
  private void reloadBoard() {
    Intent restartIntent = new Intent(this, LeaderBoardActivity.class);
    restartIntent.putExtra("BoardType", boardType);
    startActivity(restartIntent);
    finish();
  }
}
