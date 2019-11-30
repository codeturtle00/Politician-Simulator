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

    void addScore(int score){
        if (new File(context.getFilesDir() + "/" + FILE_NAME).exists()){
            saveExists(score);
        } else {
            saveNotExist(score);
        }
    }

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
