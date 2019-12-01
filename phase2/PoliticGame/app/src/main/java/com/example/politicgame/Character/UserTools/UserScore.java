package com.example.politicgame.Character.UserTools;

import android.content.Context;

import com.example.politicgame.Common.FileSavingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UserScore {
    private final String FILE_NAME = "UserScores.json";
    private Context context;
    private String displayName;
    private FileSavingService fileSaving;

    UserScore(Context context, String displayName){
        this.context = context;
        this.displayName = displayName;
        this.fileSaving = new FileSavingService(context);
    }

    /**
     * Return the user's life-time score over all characters they saved games with
     *
     * @return  The total score the user has received and saved in all time
     */
    int getTotalScore (){
        int score = 0;

        try {
            JSONArray fileArray = fileSaving.readJsonFile(FILE_NAME);

            for (int i = 0; i < fileArray.length(); i++){
                JSONObject userInfo = fileArray.getJSONObject(i);
                String currName = userInfo.keys().next();

                if (currName.equals(displayName)){
                    score = userInfo.getInt(currName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return score;
    }

    /**
     * Adds the score to the User's life-time score
     *
     * @param score The score to be added
     */
    void addScore(int score){
        if (new File(context.getFilesDir() + "/" + FILE_NAME).exists()){
            //If the file exists, then we will write to an existing file
            saveExists(score);
        } else {
            //If the file does not exist, then we will create a new file and write to it
            saveNotExist(score);
        }
    }

    /**
     * Adds the score and saves it, called only if we know the file already exists
     * @param score
     */
    private void saveExists(int score){
        try {
            JSONArray fileArray = fileSaving.readJsonFile(FILE_NAME);
            int oldScore = 0;

            for (int i = 0; i < fileArray.length(); i++){
                JSONObject userInfo = fileArray.getJSONObject(i);
                String currName = userInfo.keys().next();

                if (currName.equals(displayName)){
                    oldScore = userInfo.getInt(currName);
                }
            }

            JSONObject userObject = new JSONObject();

            userObject.put(displayName, oldScore + score);

            fileSaving.replaceJsonObject(userObject, FILE_NAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the UserScores.json file to write scores to and saves the user's new score to it
     * @param score
     */
    private void saveNotExist(int score){
        try {
            JSONObject userObject = new JSONObject();
            JSONArray userArray = new JSONArray();

            userObject.put(displayName, score);
            userArray.put(userObject);

            System.out.println(userArray.toString());
            System.out.println("Write");
            fileSaving.writeJson(FILE_NAME, userArray);
        } catch (JSONException t) {
            t.printStackTrace();
        }
    }
}
