package com.example.politicgame.Games.BabyGame;

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

  /** Whether this event has been interacted with. */
  private boolean interaction = false;

  /**
   * Creates this Event object.
   *
   * @param babyX the X coordinate of the Baby
   * @param babyY the Y coordinate of the Baby
   * @param babyWidth the width of the Baby
   * @param babyHeight the height of the Baby
   * @param res the resources used
   */
  Event(int babyX, int babyY, int babyWidth, int babyHeight, Resources res) {
    this.babyX = babyX;
    this.babyY = babyY;
    this.babyWidth = babyWidth;
    this.babyHeight = babyHeight;
    this.res = res;
    this.paint = new Paint();
    paint.setAlpha(180);
  }

  /**
   * Updates the score based on touch input values.
   *
   * @param v the View being used
   * @param initialX the X coordinate of the initial touch
   * @param initialY the Y coordinate of the initial touch
   * @param movingX
   * @param movingY
   * @param finalX the X coordinate of where the touch ended
   * @param finalY the Y coordinate of where the touch ended
   * @return value to change baby happiness by
   */
  abstract int handleTouch(
      View v,
      float initialX,
      float initialY,
      float movingX,
      float movingY,
      float finalX,
      float finalY);

  void draw(Canvas canvas) {
    System.out.println("Drawing " + img + " at " + x + "," + y);
    int adjustedX = x - imgWidth() / 2;
    int adjustedY = y - imgHeight() / 2;
    canvas.drawBitmap(img, adjustedX, adjustedY, paint);
    System.out.println("Event drawn!");
  }

  void setX(int x) {
    this.x = x;
  }

  void setY(int y) {
    this.y = y;
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

  Bitmap getImg() {
    return img;
  }

  int imgWidth() {
    return img.getWidth();
  }

  int imgHeight() {
    return img.getHeight();
  }

  void setInteraction(boolean interaction) {
    this.interaction = interaction;
  }

  boolean getInteraction() {
    return interaction;
  }
}
