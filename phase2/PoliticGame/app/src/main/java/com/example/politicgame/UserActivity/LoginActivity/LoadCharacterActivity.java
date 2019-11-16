package com.example.politicgame.UserActivity.LoginActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.GameActivity;
import com.example.politicgame.GamesActivity.BabyGame.BabyGameInstruction;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;

import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.Character.UserAccount;
import com.example.politicgame.UserActivity.RegisterActivity.SelectCharacterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class LoadCharacterActivity extends GameActivity {
  protected PoliticGameApp app;
  private final String FILE_NAME = "user.json";
  private FileSavingService fileSaving;
  private Drawable highlight;
  private int currCharacter;

  // Buttons and Textviews for navigating the screen
  private TextView charButton1;
  private TextView charButton2;
  private Button toggleExistButton1;
  private Button toggleExistButton2;
  private Button startButton;
  private TextView backButton;

  // Variables that help measure the state of the loading cells
  private Boolean[] cellLoaded;
  private String[] cellNames;
  private UserAccount userAcc;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    app = (PoliticGameApp) getApplication();

    System.out.println("The current theme is blue: " + app.isThemeBlue());

    // Set theme
    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }

    setTitle("Load Your Character");

    currCharacter = 0;
    userAcc = app.getCurrentUser();

    cellLoaded = new Boolean[] {false, false};
    cellNames = new String[] {"", ""};

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_load_character);

    highlight = getResources().getDrawable(R.drawable.highlight);
    charButton1 = findViewById(R.id.character_1);
    charButton2 = findViewById(R.id.character_2);
    toggleExistButton1 = findViewById(R.id.toggle_exist_1);
    toggleExistButton2 = findViewById(R.id.toggle_exist_2);
    startButton = findViewById(R.id.start_button);
    backButton = findViewById(R.id.load_character_back);

    Log.i("onCreate", "Before we populate cells");

    populateCharacterCells();

    if (cellLoaded[0]) {
      toggleExistButton1.setText("Delete");
    } else {
      toggleExistButton1.setText("New");
    }

    if (cellLoaded[1]) {
      toggleExistButton2.setText("Delete");
    } else {
      toggleExistButton2.setText("New");
    }

    charButton1.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            currCharacter = 1;

            charButton1.setBackground(highlight);
            charButton2.setBackground(null);
          }
        });

    charButton2.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            currCharacter = 2;

            charButton2.setBackground(highlight);
            charButton1.setBackground(null);
          }
        });

    toggleExistButton1.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            if (cellLoaded[0]) {
              deleteCharacter(cellNames[0]);
            } else {
              createNewCharacter();
            }
          }
        });

    toggleExistButton2.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            if (cellLoaded[1]) {
              deleteCharacter(cellNames[1]);
            } else {
              createNewCharacter();
            }
          }
        });

    startButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            startGame();
          }
        });

    backButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            toLoggedInMenu();
          }
        });
  }

  private void startGame() {
    if (currCharacter != 0 && cellLoaded[currCharacter - 1]) {
      app.setCurrentCharacter(cellNames[currCharacter - 1]);

      Intent babyGameIntent = new Intent(this, BabyGameInstruction.class);
      startActivity(babyGameIntent);
    }
  }

  private void populateCharacterCells() {
    JSONObject charCell = getExistingCharacters();

    Log.i("Read charCell", "THE JSON OUTPUT IS HERE");
    Log.i("Read charCell", charCell.toString());

    Boolean[] load = new Boolean[] {false, false};
    int currCell = 0;

    Iterator<String> keys = charCell.keys();
    String currKey;
    String[] msg = new String[2];

    try {
      while (keys.hasNext()) {
        msg[currCell] = "";
        currKey = keys.next();

        msg[currCell] += "Character Name: " + currKey + ".\n";

        JSONArray electionResults = charCell.getJSONObject(currKey).getJSONArray("SCORE");

        msg[currCell] += "Completed elections: " + electionResults.length() + ".\n";

        int electionsWon = 0;
        for (int i = 0; i < electionResults.length(); i++) {
          if (electionResults.getInt(i) > 200) {
            electionsWon += 1;
          }
        }

        msg[currCell] += "Elections won: " + electionsWon + ".";

        cellNames[currCell] = currKey;

        currCell++;
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    if (currCell == 2) {
      charButton1.setText(msg[0]);
      cellLoaded[0] = true;
      charButton2.setText(msg[1]);
      cellLoaded[1] = true;
    } else if (currCell == 1) {
      charButton1.setText(msg[0]);
      cellLoaded[0] = true;
      charButton2.setText("Press button to create character");
    } else {
      charButton1.setText("Press button to create character");
      charButton2.setText("Press button to create character");
    }
  }

  private JSONObject getExistingCharacters() {
    JSONArray charArray = userAcc.getCharArray();
    JSONObject returnChar = new JSONObject();

    Log.i("Get existing characters", charArray.toString());

    try {
      int i;
      for (i = 0; i < charArray.length(); i++) {
        JSONObject charObject = charArray.getJSONObject(i);
        String charName = charObject.keys().next();
        returnChar.put(charName, charObject.get(charName));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return returnChar;
  }

  private void toLoggedInMenu() {
    Intent selectIntent = new Intent(this, LoggedInActivity.class);
    startActivity(selectIntent);
    finish();
  }

  private void createNewCharacter() {
    Intent createNewCharacterIntent = new Intent(this, SelectCharacterActivity.class);
    startActivity(createNewCharacterIntent);
    finish();
  }

  private void deleteCharacter(String charName) {
    userAcc.deleteCharByName(charName);
    userAcc.saveToDb();

    Intent restartIntent = new Intent(this, LoadCharacterActivity.class);
    startActivity(restartIntent);
    finish();
  }
}
