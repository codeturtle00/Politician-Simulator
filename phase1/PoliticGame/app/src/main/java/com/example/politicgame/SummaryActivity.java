package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.Character.UserAccount;

import org.json.JSONObject;

public class SummaryActivity extends GameActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        UserAccount currentUser = app.getCurrentUser();
        JSONObject charInfo = currentUser.getCharByName(app.getCurrentCharacter());

        /**
         * "charname": {
         *     "LEVEL1":{
         *         "rating":2,
         *         "score":100,
         *         "complete":true
         *     },
         *     "LEVEL2":{
         *         "rating":2,
         *         "score":100,
         *         "complete":true
         *     },
         *     "LEVEL3":{
         *         "rating":2,
         *         "score":100,
         *         "complete":true
         *     }
         * }
         */


        final Button mainMenuButton = findViewById(R.id.summary_main_menu);
        mainMenuButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openLoggedIn();
                    }
                });
    }
}
