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
        babyImg = BitmapFactory.decodeResource(resources, R.drawable.baby);
        //width and height needs to be changed to dynamically scaled depending on holder width/height
        babyImg = Bitmap.createScaledBitmap(babyImg, 320,640, false);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(babyImg, this.x, this.y, paint);
    }
}
