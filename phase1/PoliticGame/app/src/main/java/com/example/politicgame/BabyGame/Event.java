package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Event {
    private Paint paint;
    private int x;
    private int y;
    private Resources res;
    private Bitmap img;

    Event(int x, int y, Resources res) {
        this.x = x;
        this.y = y;
        this.res = res;
        this.img = img;
        this.paint = new Paint();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(img, x, y, paint);
    }

    protected void setImg(Bitmap img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    abstract void update();

}
