package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.politicgame.BabyGame.BabyActivity;
import com.example.politicgame.User.GameCharacter;
import com.example.politicgame.User.PoliticianA;
import com.example.politicgame.User.PoliticianB;
import com.example.politicgame.User.UserAccount;
import com.example.politicgame.User.UserAccountManager;

import org.json.JSONObject;

public class SelectCharacterActivity extends AppCompatActivity {

    private PoliticGameApp app;
    private UserAccountManager userManager;
    private int currCharacter;
    private GameCharacter selectedCharacter;
    private Drawable highlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);

        setTitle("Select GameCharacter");

        currCharacter = 0;
        highlight = getResources().getDrawable(R.drawable.highlight);

        final ImageView charAButton = findViewById(R.id.imageButton);
        final ImageView charBButton = findViewById(R.id.imageButton2);
        final TextView inputName = findViewById(R.id.name_input);
        final Button submitName = findViewById(R.id.submit_name);

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
                    }
                });

    }

    public void characterSet(String name){
        selectedCharacter.setName(name);
        this.userManager.loginUser.setCharArray(selectedCharacter.getJsonCharacter());
        this.userManager.loginUser.saveToDb();

        //Sets current characters' name
        app.setCurrentCharacter(name);

        startBabyGame();
    }

  public void startBabyGame() {
      Intent switchBabyIntent = new Intent(this, BabyActivity.class);
      startActivity(switchBabyIntent);
      finish();
  }
}
