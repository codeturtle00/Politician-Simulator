package com.example.politicgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.politicgame.Character.UserAccount;
import com.example.politicgame.UserActivity.LoginActivity.LoggedInActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * GameActivity includes code every minigame activity should have.
 * To implement into a minigame's activity, extend GameActivity instead of AppCompatActivity.
 */
public abstract class GameActivity extends AppCompatActivity {

    protected PoliticGameApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }
    }

    public void openMainMenu() {
        Intent mainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(mainMenuIntent);
        finish();
    }

    public void openLoggedIn() {
        Intent loggedIntent = new Intent(this, LoggedInActivity.class);
        startActivity(loggedIntent);
        finish();
    }

    public void openSummary() {
        Intent summaryIntent = new Intent(this, SummaryActivity.class);
        startActivity(summaryIntent);
        finish();
    }


    public void openLeaderBoard() {
        Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
        startActivityForResult(switchBoardIntent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // requestCode refers to the request code parameter of openPauseMenu's startActivityForResult
        // call
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int userInput = data.getIntExtra("result", 0);

                if (userInput == 1) {
                    Log.i("Pause Result", "User has decided to resume play");
                } else if (userInput == 2) {
                    Log.i("Pause Result", "User has decided to quit the game");
                    openMainMenu();
                }
            } else {
                Log.i("Result Code", "Result code is " + resultCode);
            }
        }
    }

    public void saveGame(int score, String levelName){
        UserAccount currentUser = app.getCurrentUser();
        String currentCharacterName = app.getCurrentCharacter();

        JSONArray charArray = currentUser.getCharArray();

        Log.i("Get existing characters",charArray.toString());

        try{
            int i;
            for(i = 0; i < charArray.length(); i++){
                JSONObject charObject = charArray.getJSONObject(i);
                String charName = charObject.keys().next();

                if(charName.equals(currentCharacterName)){
                    JSONObject levelObj = charObject.getJSONObject(charName).getJSONObject(levelName);
                    levelObj.put("score", score);
                    levelObj.put("complete", true);
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        currentUser.saveToDb();
    }
}
