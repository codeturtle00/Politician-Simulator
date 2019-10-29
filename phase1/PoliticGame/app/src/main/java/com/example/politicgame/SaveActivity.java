package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.politicgame.LeaderBoardActivity;
import com.example.politicgame.PauseActivity;
import com.example.politicgame.MainActivity;
import com.example.politicgame.R;

import org.json.JSONObject;

public class SaveActivity extends AppCompatActivity {
    /**
     * When this activity is called via intent we want to save the data passed on from the intent
     * into the correct file.
     *
     * PARAMETERS NEEDED
     * userName: The user's username, used to decide which directory to write to
     * levelNum: The level's number, numbered from 1 to 3 which will decide which directory we
     * will write to. Writing 0 will refer to no level, which refers to the user as a whole. Writing
     * 4 will refer to the current leaderboards, shared by all users
     * <KEYVALUEHERE>: <KEYVALUEHERE> will be the name of the data item and the value will be the
     * data that is stored into the key
     *
     *
     * HOW IT WILL WORK
     * 1) SaveActivity is called with parameters passed
     * 2) The directory is checked for the existing file
     * 3) The file is created if it does not exist
     * 4) The level is checked, the levels are always created empty if there are new users, but if
     *    the level is 0, then it refers to the user as a whole. If it is 4 then it will save to
     *    the leaderboard instead
     * 5) The file is checked for the user and the user is created if they do not exist. If the
     *    level does not refer to 0, 1, 2, or 3, this process is skipped and we write to the
     *    leaderboard instead
     * 6) The level is written into with the keys and values that are given by the user
     * 7) SaveActivity will return a result afterwards
     */


    private final String FILENAME = "user_game_data.json";
    private final int NOLEVEL = 0;
    private final int LEVELONE = 1;
    private final int LEVELTWO = 2;
    private final int LEVELTHREE = 3;
    private final int LEADERBOARD = 4;

    private String currentUser;
    private JSONObject fileState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);


    }

    public boolean fileExists(){
        /**
         * Checks if the file exists in the directory the game will be saved in
         */


        return false;
    }

    public void createUser(String userName){
        /**
         * Creates a new user, this assumes that the user doesn't exist, so checks need to be made
         * beforehand. This will also set the current user to the user that was also created
         */

    }

    public void createFile(){
        /**
         * Creates the file in the device which we will use to store user data and other persistent
         * game info.
         */
    }

    public void saveGame (int levelNum){
        /**
         * Saves the current game
         */

    }

    public void setCurrentUser(String userName){
        /**
         * Sets currentUser to userName, makes sure the user exists first
         */
    }

    public void update(){
        /**
         * Updates
         */
    }
}
