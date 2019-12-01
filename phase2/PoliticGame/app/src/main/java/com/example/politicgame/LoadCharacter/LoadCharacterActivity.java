package com.example.politicgame.LoadCharacter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.politicgame.GameActivity;
import com.example.politicgame.GameMode.GameModeActivity;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.NewCharacter.SelectCharacterActivity;
import com.example.politicgame.PopUpActivity;
import com.example.politicgame.R;

import com.example.politicgame.Character.UserTools.UserAccount;

public class LoadCharacterActivity extends PopUpActivity {
  protected PoliticGameApp app;
  private final int TOTAL_CELLS = 2;
  private final String FILE_NAME = "user.json";
  private Drawable highlight;
  private int currCharacter;

  // Buttons and Textviews for navigating the screen
  private TextView[] charButton;
  private Button startButton;
  private ImageButton closeButton;

  /**
   * Stores the character create and delete button for cell i
   * charCreateDelButtons[i][0]: create Button for cell i
   * charCreateDelButtons[i][1]: delete Button for cell i
   */
  private View[][] charCreateDelButtons;

  // Variables that help measure the state of the loading cells
  private Boolean[] cellLoaded;
  private String[] cellNames;
  private UserAccount userAcc;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    app = (PoliticGameApp) getApplication();

    System.out.println("The current theme is blue: " + app.isThemeBlue());

    setTitle("Load Your Character");

    currCharacter = 0;
    userAcc = app.getCurrentUser();

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_load_character);

    highlight = getResources().getDrawable(R.drawable.highlight);
    charButton = new TextView [] {findViewById(R.id.character_1),findViewById(R.id.character_2)};
    charCreateDelButtons = new View[][]
            {{findViewById(R.id.create_character1), findViewById(R.id.delete_character1)},
                    {findViewById(R.id.create_character2), findViewById(R.id.delete_character2)}};
    startButton = findViewById(R.id.start_button);
    closeButton = findViewById(R.id.closeLoadCharacter);

    Log.i("onCreate", "Before we populate cells");


    // Fills cells with proper information from the user
    populateCells();

    /** Cell 1 */
    charButton[0].setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Highlight the cell
            if (cellLoaded[0]) {
              currCharacter = 1;
              charButton[0].setBackground(highlight);
              charButton[1].setBackground(null);
            }
          }
        });

    /**
     * Cell 2
     */
    charButton[1].setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            //Highlight the cell
            if (cellLoaded[1]) {
              currCharacter = 2;
              charButton[1].setBackground(highlight);
              charButton[0].setBackground(null);
            }
          }
        });

    /**
     * Sets onClickListeners for all charCreateDelButtons
     */
    for (int i = 0; i < charCreateDelButtons.length; i++) {
      final int finalI = i;

      charCreateDelButtons[i][0].setOnClickListener(
              new View.OnClickListener() {
                public void onClick(View v) {
                  createNewCharacter();
                  }
                }
              );
      charCreateDelButtons[i][1].setOnClickListener(
              new View.OnClickListener() {
                public void onClick(View v) {
                  deleteCharacter(cellNames[finalI]);
                }
              }
      );
    }

    startButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            startGame();
          }
        });

    closeButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            finish();
          }
        });
  }


  /**
   * Starts the game if you have selected a character that exists and have hit the start button
   */
  private void startGame() {
    if (currCharacter != 0 && cellLoaded[currCharacter - 1]) {
      app.setCurrentCharacter(cellNames[currCharacter - 1]);

      Intent gameModeSelectIntent = new Intent(this, GameModeActivity.class);
      overridePendingTransition(0, 0);
      startActivity(gameModeSelectIntent);
      finishAffinity();
    } else findViewById(R.id.errorText).setVisibility(View.VISIBLE);
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
      charButton[i].setText("\n" + cellData[i].getCellText()); //"\n" for centering

      // Each cell sets the visibility of their create and delete buttons
      cellData[i].setCreateDeleteButtons(charCreateDelButtons[i][0], charCreateDelButtons[i][1]);
    }
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

    recreate();
  }
}
