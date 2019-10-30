package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public abstract class Event {
  private Paint paint;
  private float x;
  private float y;
  private Resources res;
  private Bitmap img;

  Event(float x, float y, Resources res) {
    this.x = x;
    this.y = y;
    this.res = res;
    this.paint = new Paint();
  }

  public void draw(Canvas canvas) {
    canvas.drawBitmap(img, x, y, paint);
  }

  protected void setImg(Bitmap img) {
    this.img = img;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  abstract void update();

  abstract void handleTouch(View v, MotionEvent event);
}
