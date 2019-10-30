package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.politicgame.R;

public class Baby {

    private int x;
    private int y;
    private Paint paint;
    private Bitmap babyImg;

    public Baby(int x, int y, Resources resources) {
        paint = new Paint();
        this.x = x;
        this.y = y;

    }

    public void draw(Canvas canvas, Resources resources) {
        babyImg = BitmapFactory.decodeResource(resources, R.drawable.baby);
        babyImg = Bitmap.createScaledBitmap(babyImg, 320,640, false);
        canvas.drawBitmap(babyImg, this.x, this.y, paint);
    }
}
