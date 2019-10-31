package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * A superclass of every event in this game.
 */
abstract class Event {
  private float x;
  private float y;
  private Paint paint;

  private Bitmap img;
  private Resources res;

  /**
   * Updates the score based on touch input values.
   * @param v the View being used
   * @param initialX the X coordinate of the initial touch
   * @param initialY the Y coordinate of the initial touch
   * @param finalX the X coordinate of where the touch ended
   * @param finalY the Y coordinate of where the touch ended
   * @return value to change baby happiness by
   */
  abstract int update(View v, float initialX, float initialY, float finalX, float finalY);

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

  void setImg(Bitmap img) {
    this.img = img;
  }
}
