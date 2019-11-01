package com.example.politicgame.UserActivity.LoginActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

<<<<<<< HEAD:phase1/PoliticGame/app/src/main/java/com/example/politicgame/UserActivity/LoginActivity/LoadCharacterActivity.java
import com.example.politicgame.GameActivity;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.UserActivity.LoginActivity.LoggedInActivity;
=======
import androidx.appcompat.app.AppCompatActivity;

import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.User.UserAccount;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;
>>>>>>> af75400ed92afdf7a6b26a964324d9ae7731f988:phase1/PoliticGame/app/src/main/java/com/example/politicgame/LoadCharacterActivity.java

public class LoadCharacterActivity extends GameActivity {
    protected PoliticGameApp app;
    private final String FILE_NAME = "user.json";
    private FileSavingService fileSaving;
    private Drawable highlight;
    private int currCharacter;

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

        this.fileSaving = new FileSavingService(this);

        final TextView charButton1 = findViewById(R.id.character_1);
        final TextView charButton2 = findViewById(R.id.character_2);
        final Button toggleExistButton1 = findViewById(R.id.toggle_exist_1);
        final Button toggleExistButton2 = findViewById(R.id.toggle_exist_2);
        final Button startButton = findViewById(R.id.start_button);
        final TextView backButton = findViewById(R.id.load_character_back);

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

    private void toLoggedInMenu() {
        Intent selectIntent = new Intent(this, LoggedInActivity.class);
        startActivity(selectIntent);
    }

    private JSONObject getExistingCharacters(){
        UserAccount userAcc = app.getCurrentUser();

        //JSONArray charArray = userAcc.

        return new JSONObject();
    }
}
