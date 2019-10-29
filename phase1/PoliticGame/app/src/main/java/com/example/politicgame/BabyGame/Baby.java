package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.politicgame.R;

public class Baby {

    private Paint paint;

    public Baby() {
        paint = new Paint();
    }

    public void draw(Canvas canvas, Resources resources) {
        Bitmap baby = BitmapFactory.decodeResource(resources, R.drawable.baby);
        baby = Bitmap.createScaledBitmap(baby, 320, 640, false);
        canvas.drawBitmap(baby, 0, 0, paint);
    }
}
