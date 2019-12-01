package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;

import com.example.politicgame.Games.StampGame.StampInstructionActivity;

public class SpeechArcade extends ArcadeMode{
    private static final String LEVEL_NAME = "LEVEL2";

    public SpeechArcade(){ super(LEVEL_NAME); }

    /**
     * Returns the Intent to the next required activity
     *
     * @param lastActivity  The activity this class was instantiated in
     * @return              The Intent to the next required activity
     */
    public Intent next(Context lastActivity){
        Intent switchStampIntent = new Intent(lastActivity, StampInstructionActivity.class);
        switchStampIntent.putExtra("GameMode", new StampArcade());
        return switchStampIntent;
    }
}
