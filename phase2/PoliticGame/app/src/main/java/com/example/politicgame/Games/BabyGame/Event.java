package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/** A superclass of every event in this game. */
abstract class Event {

  /** The Baby this event is acting upon */
  private Baby baby;

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
   * @param baby The Baby this event is acting upon
   * @param res the resources to draw the baby
   */
  Event(Baby baby, Resources res) {
    this.baby = baby;
    this.babyX = baby.getX();
    this.babyY = baby.getY();
    this.babyWidth = baby.getWidth();
    this.babyHeight = baby.getHeight();
    this.res = res;
    this.paint = new Paint();
    paint.setAlpha(250);
  }

  /**
   * Updates the score based on touch input values.
   *
   * @param v the View being used
   * @param initialX the X coordinate of the initial touch
   * @param initialY the Y coordinate of the initial touch
   * @param movingX the updated X coordinate from finger movement
   * @param movingY the updated Y coordinate from finger movement
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

  /**
   * Draws this event at the set coordinates.
   *
   * @param canvas the canvas which to draw the event
   */
  void draw(Canvas canvas) {
    paint.setAlpha(220);
    System.out.println("Drawing " + img + " at " + x + "," + y);
    int adjustedX = x - imgWidth() / 2;
    int adjustedY = y - imgHeight() / 2;
    canvas.drawBitmap(img, adjustedX, adjustedY, paint);
    System.out.println("Event drawn!");
  }

  /** Returns Baby */
  Baby getBaby() {
    return baby;
  }

  /** Returns original X coordinate of baby */
  int getBabyX() {
    return this.babyX;
  }

  /** Returns original Y coordinate of baby */
  int getBabyY() {
    return this.babyY;
  }

  /**
   * Sets X coordinate of event.
   *
   * @param x X coordinate of event
   */
  void setX(int x) {
    this.x = x;
  }

  /**
   * Sets Y coordinate of event.
   *
   * @param y Y coordinate of event
   */
  void setY(int y) {
    this.y = y;
  }

  /** Returns X coordinate of event. */
  int getX() {
    return x;
  }

  /** Returns Y coordinate of event. */
  int getY() {
    return y;
  }

  /** Returns width of baby. */
  int getBabyWidth() {
    return babyWidth;
  }

  /** Returns height of baby. */
  int getBabyHeight() {
    return babyHeight;
  }

  /**
   * Sets this event's image
   *
   * @param img this event's image
   */
  void setImg(Bitmap img) {
    this.img = img;
  }

  /** Returns this event's image. */
  Bitmap getImg() {
    return img;
  }

  /** Returns the width of the event's image. */
  int imgWidth() {
    return img.getWidth();
  }

  /** Returns the height of the event's image. */
  int imgHeight() {
    return img.getHeight();
  }

  /**
   * Sets this event to be interacted with.
   *
   * @param interaction whether this event has been interacted with
   */
  void setInteraction(boolean interaction) {
    this.interaction = interaction;
  }

  /** Returns if this event has been interacted with. */
  boolean getInteraction() {
    return interaction;
  }
}
