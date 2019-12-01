package com.example.politicgame.Application;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

public class ThemeLoader {
    private boolean isBlue = true;
    private PoliticGameApp app;

    ThemeLoader(PoliticGameApp app){
        this.app = app;

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this.app);
        isBlue = mPrefs.getBoolean("isBlue", true);
    }

    /**
     * Choose the new theme
     *
     * @param isBlue Choose if the current theme is blue
     */
    void chooseBlueTheme(boolean isBlue) { this.isBlue = isBlue; }

    /**
     * Returns if the theme is currently blue
     *
     * @return  Is the current theme blue?
     */
    public boolean isThemeBlue() {
        return isBlue;
    }
}
