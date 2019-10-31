package com.example.politicgame;

import android.app.Application;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

public class PoliticGameApp extends Application {

    //Thematic selection
    private boolean isBlue = true;


    private String currentUser;
    private String currentCharacter;

    //Music player settings
    private MediaPlayer musicPlayer;
    private boolean musicOn;
    private int currentTrack;

    //Song selections
    private final int TRACK_ONE = R.raw.senko_op;
    private final int TRACK_TWO = R.raw.senko_ed;

    //The music player will iterate over these songs as we change music
    private final int [] TRACK_ARRAY = new int[] {TRACK_ONE, TRACK_TWO};

    public void onCreate(){
        super.onCreate();

        //Theme selector
        SharedPreferences mPrefs =  PreferenceManager.getDefaultSharedPreferences(this);
        isBlue = mPrefs.getBoolean("isBlue", true);

        //Music player
        currentTrack = 0; //Current track is TRACK_ONE
        musicPlayer = MediaPlayer.create(this, TRACK_ARRAY[currentTrack]);
        musicPlayer.setLooping(true);
        startMusic();
    }



    //Methods for theme selection
    public void chooseBlueTheme(boolean isBlue){
        this.isBlue = isBlue;
        System.out.println("The current theme is blue: " + isBlue);
    }

    public boolean isThemeBlue (){
        return isBlue;
    }



    public String getCurrentUser(){return currentUser;}

    public void setCurrentUser(String newUser){this.currentUser = newUser;}

    public String getCurrentCharacter(){return currentCharacter;}

    public void getCurrentUser(String newCharacter){this.currentCharacter = newCharacter;}



    //Methods for music selection
    public void switchMusic (){
        if (currentTrack < TRACK_ARRAY.length-1){
            currentTrack++;
        }
        else {
            currentTrack = 0;
        }

        //Destroys the previous music player and creates a new one with the new track
        musicPlayer.release();
        musicPlayer = MediaPlayer.create(this, TRACK_ARRAY[currentTrack]);
        musicPlayer.setLooping(true);
        startMusic();
    }

    public int getCurrentTrackNum(){
        return currentTrack + 1;
    }

    public boolean isMusicOn(){
        return musicOn;
    }

    public void startMusic (){
        musicPlayer.start();
        musicOn = true;
    }

    public void pauseMusic (){
        musicPlayer.pause();
        musicOn = false;
    }

    public void toggleMusic (){
        if (musicOn){
            pauseMusic();
        }
        else {
            startMusic();
        }
    }
}
