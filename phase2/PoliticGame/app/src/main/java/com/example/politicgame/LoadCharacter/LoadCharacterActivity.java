package com.example.politicgame.LoadCharacter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.GameActivity;
import com.example.politicgame.Games.BabyGame.BabyGameInstruction;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.NewCharacter.SelectCharacterActivity;
import com.example.politicgame.R;

import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.UserActivity.LoginActivity.LoggedInActivity;

public class LoadCharacterActivity extends GameActivity {
  protected PoliticGameApp app;
  private final int TOTAL_CELLS = 2;
  private final String FILE_NAME = "user.json";
  private FileSavingService fileSaving;
  private Drawable highlight;
  private int currCharacter;

  // Buttons and Textviews for navigating the screen
  private TextView[] charButton;
  private Button[] toggleExistButton;
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

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_load_character);

    highlight = getResources().getDrawable(R.drawable.highlight);
    charButton = new TextView [] {findViewById(R.id.character_1),findViewById(R.id.character_2)};
    toggleExistButton = new Button [] {findViewById(R.id.toggle_exist_1),findViewById(R.id.toggle_exist_2)};
    startButton = findViewById(R.id.start_button);
    backButton = findViewById(R.id.load_character_back);

    Log.i("onCreate", "Before we populate cells");


    // Fills cells with proper information from the user
    populateCells();


    /**
     * Cell 1
     */
    charButton[0].setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            currCharacter = 1;

            //Highlight the cell
            charButton[0].setBackground(highlight);
            charButton[1].setBackground(null);
          }
        });

    /**
     * Cell 2
     */
    charButton[1].setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            currCharacter = 2;

            //Highlight the cell
            charButton[1].setBackground(highlight);
            charButton[0].setBackground(null);
          }
        });

    /**
     * New Character/Delete Character button, depends on if there is a character that already exists
     */
    toggleExistButton[0].setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            if (cellLoaded[0]) {
              deleteCharacter(cellNames[0]);
            } else {
              createNewCharacter();
            }
          }
        });

    /**
     * New Character/Delete Character button, depends on if there is a character that already exists
     */
    toggleExistButton[1].setOnClickListener(
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


  /**
   * Starts the game if you have selected a character that exists and have hit the start button
   */
  private void startGame() {
    if (currCharacter != 0 && cellLoaded[currCharacter - 1]) {
      app.setCurrentCharacter(cellNames[currCharacter - 1]);

      Intent babyGameIntent = new Intent(this, BabyGameInstruction.class);
      startActivity(babyGameIntent);
      finish();
    }
  }


  /**
   * Uses the FillCellFacade to set the cells with the proper information
   */
  private void populateCells(){
    FillCellFacade fcf = new FillCellFacade(userAcc);
    CellInfo[] cellData = fcf.getCells();

    // We initialize the variables here because we don't know how many cells we have
    cellLoaded = new Boolean[cellData.length];
    cellNames = new String[cellData.length];

    // With the information we received from the cells, load the data into text
    for (int i = 0; i < cellData.length; i++){
      cellLoaded[i] = cellData[i].isLoaded();
      cellNames[i] = cellData[i].getCharName();
      charButton[i].setText(cellData[i].getCellText());
      toggleExistButton[i].setText(cellData[i].getButtonText());
    }
  }


  /**
   * Brings you back to the logged in menu
   */
  private void toLoggedInMenu() {
    Intent selectIntent = new Intent(this, LoggedInActivity.class);
    startActivity(selectIntent);
    finish();
  }


  /**
   *  Brings you to a new intent to create a new character
   */
  private void createNewCharacter() {
    Intent createNewCharacterIntent = new Intent(this, SelectCharacterActivity.class);
    startActivity(createNewCharacterIntent);
    finish();
  }

  /**
   * Deletes the character with the name charName
   *
   * @param charName The current character's name
   */
  private void deleteCharacter(String charName) {
    userAcc.deleteCharByName(charName);
    userAcc.saveToDb();

    Intent restartIntent = new Intent(this, LoadCharacterActivity.class);
    startActivity(restartIntent);
    finish();
  }
}
