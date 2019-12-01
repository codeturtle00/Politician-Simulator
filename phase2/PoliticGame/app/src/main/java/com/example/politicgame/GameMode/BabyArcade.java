package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;

import com.example.politicgame.Games.SpeechGame.SpeechInstructionActivity;

public class BabyArcade extends ArcadeMode{
    private static final String LEVEL_NAME = "LEVEL1";

    BabyArcade(){ super(LEVEL_NAME); }

    /**
     * Returns the Intent to the next required activity
     *
     * @param lastActivity  The activity this class was instantiated in
     * @return              The Intent to the next required activity
     */
    public Intent next(Context lastActivity){
        Intent switchSpeechIntent = new Intent(lastActivity, SpeechInstructionActivity.class);
        switchSpeechIntent.putExtra("GameMode", new SpeechArcade());
        return switchSpeechIntent;
    }
}
