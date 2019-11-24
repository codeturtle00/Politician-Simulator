package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;

import com.example.politicgame.Application.PoliticGameApp;

public abstract class StampGameMode implements GameMode {
    protected final String LEVEL_NAME = "LEVEL3";
    protected PoliticGameApp app;

    StampGameMode(PoliticGameApp app){
        this.app = app;
    }

    public abstract Intent next(Context lastActivity);
    public abstract void save(int score);
}