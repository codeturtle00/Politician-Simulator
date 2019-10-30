package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.View;

import com.example.politicgame.R;

public class Kiss extends Event {


    Kiss(float x, float y, Resources res) {
        super(x, y, res);
        Bitmap lips = BitmapFactory.decodeResource(res, R.drawable.baby);
        // in the future, dynamically scale lips
        setImg(lips);
    }

    public void update() {

    }

    @Override
    void handleTouch(View v, MotionEvent event) {

    }
}
