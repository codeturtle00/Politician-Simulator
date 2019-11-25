package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;

import com.example.politicgame.Application.PoliticGameApp;

public interface GameMode {
    public Intent next(Context lastActivity);
    public void save(PoliticGameApp app, int score);
    public boolean isGameComplete(PoliticGameApp app);
}
