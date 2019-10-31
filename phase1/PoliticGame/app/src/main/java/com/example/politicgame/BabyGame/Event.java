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
  protected final int babyHeight;
  protected final int babyWidth;
  protected final int babyY;
  protected final int babyX;

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

  Event(int babyX, int babyY, int babyWidth, int babyHeight, Resources res) {
    this.babyX = babyX;
    this.babyY = babyY;
    this.babyWidth = babyWidth;
    this.babyHeight = babyHeight;
    this.res = res;
    this.paint = new Paint();

  }

  abstract int determineXCoordinate();

  abstract int determineYCoordinate();

  void draw(Canvas canvas) {
    canvas.drawBitmap(img, x, y, paint);
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
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
