package com.example.politicgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.politicgame.Common.FileSavingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class LeaderBoardActivity extends AppCompatActivity {

    private PoliticGameApp app;
    private final String FILE_NAME = "user_game_data.json";
    private FileSavingService fileSaving;

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
        setContentView(R.layout.activity_leader_board);

        this.fileSaving = new FileSavingService(this);

        updateBoard();

        final Button button = findViewById(R.id.back);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        BackToMenu();
                    }
                });
    }

    public void BackToMenu() {
        Intent switchBabyIntent = new Intent(this, MainActivity.class);
        startActivity(switchBabyIntent);
    }

    public void updateBoard(){
        //The JSONArray will come from Jacky's API for reading JSON
        JSONObject charScores = getBoard();

        JSONObject first = new JSONObject();
        JSONObject second = new JSONObject();
        JSONObject third = new JSONObject();
        try{
            Iterator<String> fillThree = charScores.keys();
            first.put("filler", new JSONObject().put("score", 0));
            second.put("filler", new JSONObject().put("score", 0));
            third.put("filler", new JSONObject().put("score", 0));

            Iterator<String> charNames = charScores.keys();

            String charName;
            int charScoreNum;

            while (charNames.hasNext()){
                charName = charNames.next();
                charScoreNum = charScores.getJSONObject(charName).getInt("score");

                Log.i("First", first.toString());
                Log.i("Second", second.toString());
                Log.i("Third", third.toString());
                Log.i("charScores(name)", charScores.getJSONObject(charName).toString());

                if (charScoreNum > first.getJSONObject((first.keys().next())).getInt("score")){
                    third = second;
                    second = first;
                    first = new JSONObject().put(charName,charScores.getJSONObject(charName));
                }
                else if (charScoreNum > second.getJSONObject((second.keys().next())).getInt("score")){
                    third = second;
                    second = new JSONObject().put(charName,charScores.getJSONObject(charName));
                }
                else if (charScoreNum > third.getJSONObject((third.keys().next())).getInt("score")){
                    third = new JSONObject().put(charName,charScores.getJSONObject(charName));
                }
            }


            //The actual display of the leaderboard
            final TextView player1 = findViewById(R.id.player1);
            player1.setText("First\nUser: " + first.getJSONObject(first.keys().next()).getString("userName") +
                    "\nCharacter: " + first.keys().next() + "\nScore: " + first.getJSONObject(first.keys().next()).getInt("score"));

            final TextView player2 = findViewById(R.id.player2);
            player2.setText("Second\nUser: " + second.getJSONObject(second.keys().next()).getString("userName") +
                    "\nCharacter: " + second.keys().next() + "\nScore: " + second.getJSONObject(second.keys().next()).getInt("score"));

            final TextView player3 = findViewById(R.id.player3);
            player3.setText("Third\nUser: " + third.getJSONObject(third.keys().next()).getString("userName") +
                    "\nCharacter: " + third.keys().next() + "\nScore: " + third.getJSONObject(third.keys().next()).getInt("score"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getBoard(){

        JSONArray jsonList = this.fileSaving.readJsonFile(FILE_NAME);

        JSONObject boardList = new JSONObject();

        try {
        // A JSON files containing the User, their characters and their scores
            for (int i = 0; i < jsonList.length(); i++){
                Iterator<String> userKeys = jsonList.getJSONObject(i).keys();
                while (userKeys.hasNext()) {
                    String userKey = userKeys.next();//String version of the userName

                    JSONObject userInfo = jsonList.getJSONObject(i).getJSONObject(userKey);
                    Iterator<String> charKeys = userInfo.keys();
                    while (charKeys.hasNext()){
                        String charKey = charKeys.next();

                        JSONObject charScore = new JSONObject();
                        charScore.put("userName", userKey);
                        charScore.put("score", userInfo.getJSONObject(charKey).getInt("highScore"));

                        boardList.put(charKey, charScore);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return boardList;


        /**
         *
         *
         * [
         *  {
         *   "user": [
         *     "char" :
         *      highscore: 0,
         *      Level1: {},
         *      Level2: {},
         *      Level3: {}
         *   ]
         *  }
         * ]
         *
         * JSONObject char = new JSONObject();
         * char.put("highscore", 0);
         * char.put("objectkey", objectvalue)
         *
         *
         */
    }
}