package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.politicgame.R;

class Baby {
  /** This Baby's X coordinate. */
  private int x;

  /** This Baby's Y coordinate. */
  private int y;

  /** The original X coordinate of this Baby */
  private int originalX;

  /** The original Y coordinate of this Baby */
  private int originalY;

  /** This Baby's width. */
  private int width;

  /** This Baby's height. */
  private int height;

  /** This Baby's paint. */
  private Paint paint;

  /** The image to draw. */
  private Bitmap babyImg;

  /**
   * Creates a new Baby object.
   *
   * @param centerX the X coordinate of the center of the Baby
   * @param centerY the Y coordinate of the center of the Baby
   * @param res the resources used to draw the Baby
   */
  Baby(int centerX, int centerY, Resources res, int width) {
    paint = new Paint();

    // Height is set in proportion with width
    this.width = width;
    this.height = width * 44 / 29;

    babyImg = BitmapFactory.decodeResource(res, R.drawable.baby);
    babyImg = Bitmap.createScaledBitmap(babyImg, width, height, false);

    x = centerX - (width / 2);
    y = centerY - (height / 2);
    originalX = x;
    originalY = y;
  }

  /**
   * Draws this Baby object.
   *
   * @param canvas the canvas to draw the Baby on
   */
  void draw(Canvas canvas) {
    canvas.drawBitmap(babyImg, x, y, paint);
    System.out.println("Drew baby");
  }

  /**
   * Sets this Baby to be crying.
   *
   * @param res the resources used to draw the Baby
   */
  void setCry(Resources res) {
    babyImg = BitmapFactory.decodeResource(res, R.drawable.crybaby);
    babyImg = Bitmap.createScaledBitmap(babyImg, width, height, false);
  }

  /**
   * Sets this Baby to be sad.
   *
   * @param res the resources used to draw the Baby
   */
  void setSad(Resources res) {
    babyImg = BitmapFactory.decodeResource(res, R.drawable.sadbaby);
    babyImg = Bitmap.createScaledBitmap(babyImg, width, height, false);
  }

  /**
   * Sets this Baby to be happy.
   *
   * @param res the resources used to draw the Baby
   */
  void setHappy(Resources res) {
    babyImg = BitmapFactory.decodeResource(res, R.drawable.baby);
    babyImg = Bitmap.createScaledBitmap(babyImg, width, height, false);
  }

  /** Resets the baby's coordinates to the original ones when drawn. */
  void resetCoordinates() {
    this.x = originalX;
    this.y = originalY;
  }

  /** Returns the original X coordinate of the baby. */
  int getX() {
    return this.originalX;
  }

  /** Returns the original Y coordinate of the baby. */
  int getY() {
    return this.originalY;
  }

  /** Sets the X coordinate of the baby. */
  void setX(int x) {
    this.x = x;
  }

  /** Sets the Y coordinate of the baby. */
  void setY(int y) {
    this.y = y;
  }

  /** Returns the width of the baby. */
  int getWidth() {
    return width;
  }

  /** Returns the height of the baby. */
  int getHeight() {
    return height;
  }
}