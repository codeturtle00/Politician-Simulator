package com.example.politicgame.UserActivity.RegisterActivity;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.politicgame.Character.UserAccount;
import com.example.politicgame.GamesActivity.BabyGame.BabyActivity;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.UserActivity.LoginActivity.LoggedInActivity;

public class SelectCharacterActivity extends AppCompatActivity {

    private PoliticGameApp app;
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
        UserAccount user = app.getCurrentUser();
        user.addCharArray(selectedCharacter.getJsonCharacter());
        System.out.println("Saved!!!");
        user.saveToDb();
        //this.userManager.loginUser.saveToDb();

        //Sets current characters' name
        app.setCurrentCharacter(name);

        startGame();
    }

  public void startGame() {
      Intent startGameIntent = new Intent(this, BabyActivity.class);
      startActivity(startGameIntent);
      finish();
  }
}
