package com.example.politicgame;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PoliticGameApp extends Application {

    private boolean isBlue = true;

    public void onCreate(){
        super.onCreate();

        SharedPreferences mPrefs =  PreferenceManager.getDefaultSharedPreferences(this);
        isBlue = mPrefs.getBoolean("isBlue", true);
    }

    public void chooseBlueTheme(boolean isBlue){
        this.isBlue = isBlue;
        System.out.println("The current theme is blue: " + isBlue);
    }

    public boolean isThemeBlue (){
        return isBlue;
    }
}
