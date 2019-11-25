package com.example.politicgame.GameEnd;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.politicgame.Character.SpriteSetter;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.GameActivity;
import com.example.politicgame.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SingleEndActivity extends GameActivity{
    private final String SCORE_ID = "score";
    private final String LEVEL_ID = "level_name";
    private TextView resultText;
    private int score;
    private String levelName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_end);

        // Set Image
        ImageView resultsImage = findViewById(R.id.results_sprite);
        SpriteSetter ss = new SpriteSetter(app);
        ss.setSprite(resultsImage);

        // Set text
        resultText = findViewById(R.id.results_text);
        score = getIntent().getIntExtra(SCORE_ID, 0);
        levelName = getIntent().getStringExtra(LEVEL_ID);
        resultText.setText("Your score for this game was: " + score);

        // Set the buttons
        final Button saveMainMenuButton = findViewById(R.id.single_save_main_menu); // Save the score
        saveMainMenuButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Set up the saver first
                        SaveInfo saveData = new SaveInfo(app.getCurrentUser(), app.getCurrentCharacter(), score);
                        saveData.singleSaveInfo(levelName);

                        openLoggedIn();
                    }
                });

        final Button quitMainMenuButton = findViewById(R.id.single_quit_main_menu); // Quit to the main menu
        quitMainMenuButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        openLoggedIn();
                    }
                });
    }
}
