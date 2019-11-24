package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.Games.SpeechGame.SpeechInstructionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BabySingle extends BabyGameMode{
    PoliticGameApp app;

    BabySingle(PoliticGameApp app){
        super(app);
    }

    //TODO: Implement this
    public Intent next(Context lastActivity){
        return null; //TODO: REMOVE THIS
    }

    //TODO: Implement this, which includes implementing a seperate JSON writing system
    public void save(int score){

    }
}
