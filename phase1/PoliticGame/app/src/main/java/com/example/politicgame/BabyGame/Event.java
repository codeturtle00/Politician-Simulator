package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

abstract class Event {
  private float x;
  private float y;
  private int deltaScore;
  private Paint paint;

  private Bitmap img;
  private Resources res;

  abstract int update();

  abstract void handleTouch(View v, float initialX, float initialY, float finalX, float finalY);

  Event(float x, float y, Resources res) {
    this.x = x;
    this.y = y;
    this.res = res;
    this.paint = new Paint();
  }

  void draw(Canvas canvas) {
    canvas.drawBitmap(img, x, y, paint);
  }

  float getX() {
    return x;
  }

  float getY() {
    return y;
  }

  void setDeltaScore(int deltaScore) {
    this.deltaScore = deltaScore;
  }

  int getDeltaScore() {
    return deltaScore;
  }

  void setImg(Bitmap img) {
    this.img = img;
  }
}
