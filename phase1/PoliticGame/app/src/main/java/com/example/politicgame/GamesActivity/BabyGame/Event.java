package com.example.politicgame.GamesActivity.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/** A superclass of every event in this game. */
abstract class Event {

  /** The Baby's X coordinate. */
  private final int babyX;

  /** The Baby's Y coordinate. */
  private final int babyY;

  /** The Baby's width. */
  private final int babyWidth;

  /** The Baby's height. */
  private final int babyHeight;

  /** The X coordinate to create the event. */
  private int x;

  /** The Y coordinate to create the event. */
  private int y;

  /** The paint to draw this event. */
  private Paint paint;

  /** This event's image. */
  private Bitmap img;

  /** The resources to draw the image. */
  private Resources res;

  /** Canvas to draw events. */
  private Canvas canvas;

  /**
   * Creates this Event object.
   *
   * @param babyX the X coordinate of the Baby
   * @param babyY the Y coordinate of the Baby
   * @param babyWidth the width of the Baby
   * @param babyHeight the height of the Baby
   * @param res the resources used
   */
  Event(int babyX, int babyY, int babyWidth, int babyHeight, Resources res, Canvas canvas) {
    this.babyX = babyX;
    this.babyY = babyY;
    this.babyWidth = babyWidth;
    this.babyHeight = babyHeight;
    this.res = res;
    this.canvas = canvas;
    this.paint = new Paint();
  }

  /**
   * Updates the score based on touch input values.
   *
   * @param v the View being used
   * @param initialX the X coordinate of the initial touch
   * @param initialY the Y coordinate of the initial touch
   * @param finalX the X coordinate of where the touch ended
   * @param finalY the Y coordinate of where the touch ended
   * @return value to change baby happiness by
   */
  abstract int handleTouch(View v, float initialX, float initialY, float finalX, float finalY);

  void draw() {
    canvas.drawBitmap(img, x, y, paint);
  }

  void setX() {
    x = (int) (Math.random() * (babyWidth + 1) + babyX);
  }

  void setY() {
    y = (int) (Math.random() * (babyHeight + 1) + babyY);
  }

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  void setImg(Bitmap img) {
    this.img = img;
  }
}
