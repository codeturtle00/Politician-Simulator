package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;

import com.example.politicgame.Application.PoliticGameApp;

public abstract class BabyGameMode implements GameMode {
    protected final String LEVEL_NAME = "LEVEL1";
    protected PoliticGameApp app;

    BabyGameMode(PoliticGameApp app){
        this.app = app;
    }

    public abstract Intent next(Context lastActivity);
    public abstract void save(int score);
}
