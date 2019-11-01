package com.example.politicgame;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.User.UserAccount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Iterator;

public class LoadCharacterActivity extends GameActivity {
    protected PoliticGameApp app;
    private final String FILE_NAME = "user.json";
    private FileSavingService fileSaving;
    private Drawable highlight;
    private int currCharacter;
    final TextView charButton1 = findViewById(R.id.character_1);
    final TextView charButton2 = findViewById(R.id.character_2);
    final Button toggleExistButton1 = findViewById(R.id.toggle_exist_1);
    final Button toggleExistButton2 = findViewById(R.id.toggle_exist_2);
    final Button startButton = findViewById(R.id.start_button);
    final TextView backButton = findViewById(R.id.load_character_back);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        //Set theme
        if (app.isThemeBlue()) {
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        setTitle("Load Your Character");

        currCharacter = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_character);

        highlight = getResources().getDrawable(R.drawable.highlight);

        populateCharacterCells();

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

                    }
                });

        toggleExistButton2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                    }
                });

        startButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                    }
                });

        backButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        toLoggedInMenu();
                    }
                });
    }


    private void populateCharacterCells(){
        JSONObject charCell = getExistingCharacters();

        Boolean [] load = new Boolean [] {false, false};
        int currCell = 0;

        Iterator<String> keys = charCell.keys();
        String currKey;
        String msg;
        while(keys.hasNext()){
            msg = "";
            currKey = keys.next();

            msg += "Character Name: " + currKey + ".";
            msg += "Character Name: " + currKey + ".";
        }
    }

    private JSONObject getExistingCharacters(){
        UserAccount userAcc = app.getCurrentUser();
        JSONArray charArray = userAcc.getCharArray();
        JSONObject returnChar = new JSONObject();

        try{
            int i;
            for(i = 0; i < charArray.length(); i++){
                JSONObject charObject = charArray.getJSONObject(i);
                String charName = charObject.keys().next();
                returnChar.put(charName, charObject.get(charName));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return returnChar;
    }

    private void toLoggedInMenu() {
        Intent selectIntent = new Intent(this, LoggedInActivity.class);
        startActivity(selectIntent);
    }
}
