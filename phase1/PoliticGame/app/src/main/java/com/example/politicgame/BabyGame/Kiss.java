package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.politicgame.R;

public class Kiss extends Event {


    Kiss(int x, int y, Resources res) {
        super(x, y, res);
        Bitmap lips = BitmapFactory.decodeResource(res, R.drawable.baby);
        // in the future, dynamically scale lips
        setImg(lips);
    }

    public void update() {

    }
}
