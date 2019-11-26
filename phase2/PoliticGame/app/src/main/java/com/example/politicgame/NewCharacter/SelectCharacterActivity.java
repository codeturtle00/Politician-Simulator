package com.example.politicgame.NewCharacter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.politicgame.Character.GameCharacter;
import com.example.politicgame.Character.PoliticianA;
import com.example.politicgame.Character.PoliticianB;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.GameActivity;
import com.example.politicgame.GameMode.GameModeActivity;
import com.example.politicgame.Games.BabyGame.BabyGameInstruction;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SelectCharacterActivity extends GameActivity {
  private int currCharacter;
  private GameCharacter selectedCharacter;
  private Drawable highlight;
  private PoliticGameApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (PoliticGameApp) getApplication();
        setContentView(R.layout.activity_select_character);

        setTitle("Select Game Character");

        currCharacter = 0;
        highlight = getResources().getDrawable(R.drawable.highlight);

        final ImageView charAButton = findViewById(R.id.imageButton);
        final ImageView charBButton = findViewById(R.id.imageButton2);
        final TextView inputName = findViewById(R.id.name_input);
        final Button submitName = findViewById(R.id.submit_name);
        final Button backButton = findViewById(R.id.backButton);
        final TextView error_name = findViewById(R.id.error_name);
        final TextView error_select = findViewById(R.id.error_character);

        //Character A is selected
        charAButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        currCharacter = 1;
                        selectedCharacter = new PoliticianA();

                        charAButton.setBackground(highlight);
                        charBButton.setBackground(null);
                    }
                });

        //Character B is selected
        charBButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        currCharacter = 2;
                        selectedCharacter = new PoliticianB();

                        charBButton.setBackground(highlight);
                        charAButton.setBackground(null);
                    }
                });


        submitName.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        String name = inputName.getText().toString();
                        if (currCharacter != 0 && !name.equals(null) && !name.equals("")){
                            characterSet(name);
                        }

                        if (currCharacter == 0){
                            error_select.setText("Please select a character");
                        } else {
                            error_select.setText("");
                        }

                        if (name.equals("")){
                            error_name.setText("Enter a character name");
                        } else {
                            error_name.setText("");
                        }
                    }
                });

        backButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        openLoggedIn();
                        app.getCurrentUser().setCurrentCharacter(selectedCharacter);
                    }
                });
    }



    public void characterSet(String name){
        selectedCharacter.setName(name);
        UserAccount user = app.getCurrentUser();

        JSONObject newChar = selectedCharacter.getJsonCharacter();
        String charName = newChar.keys().next();

        try{
            JSONObject charInfo = newChar.getJSONObject(charName);
            charInfo.getJSONObject("LEVEL1").put("complete", false);
            charInfo.getJSONObject("LEVEL2").put("complete", false);
            charInfo.getJSONObject("LEVEL3").put("complete", false);

        } catch (JSONException e){
            e.printStackTrace();
        }

        user.addCharArray(newChar);
        System.out.println("Saved!!!");
        user.saveToDb();

        //Sets current characters' name
        app.setCurrentCharacter(name);

        startGameModeSelection();
    }




  public void startGameModeSelection() {
      Intent gameModeSelectIntent = new Intent(this, GameModeActivity.class);
      startActivity(gameModeSelectIntent);
      finish();
  }
}
