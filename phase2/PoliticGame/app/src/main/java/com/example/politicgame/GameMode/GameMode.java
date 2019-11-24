package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;

public interface GameMode {
    public Intent next(Context lastActivity);
    public void save(int score);
}
