package com.example.politicgame;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.fragment.app.FragmentActivity;

import com.example.politicgame.Application.PoliticGameApp;

public abstract class PopUpActivity extends FragmentActivity {

    /** The application. */
    protected PoliticGameApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int)(height*.8));

        app = (PoliticGameApp) getApplication();

        setTheme(R.style.PopUp);
    }
}
