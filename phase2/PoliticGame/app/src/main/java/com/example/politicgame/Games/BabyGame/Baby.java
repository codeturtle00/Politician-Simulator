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
  Baby(int centerX, int centerY, Resources res) {
    paint = new Paint();

    babyImg = BitmapFactory.decodeResource(res, R.drawable.baby);
    babyImg = Bitmap.createScaledBitmap(babyImg, 640, 971, false);
    // width and height needs to be changed to dynamically scaled depending on holder width/height
    width = babyImg.getWidth();
    height = babyImg.getHeight();
    x = centerX - (width / 2);
    y = centerY - (height / 2);
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
    babyImg = Bitmap.createScaledBitmap(babyImg, 640, 971, false);
  }

  /**
   * Sets this Baby to be sad.
   *
   * @param res the resources used to draw the Baby
   */
  void setSad(Resources res) {
    babyImg = BitmapFactory.decodeResource(res, R.drawable.sadbaby);
    babyImg = Bitmap.createScaledBitmap(babyImg, 640, 971, false);
  }

  /**
   * Sets this Baby to be happy.
   *
   * @param res the resources used to draw the Baby
   */
  void setHappy(Resources res) {
    babyImg = BitmapFactory.decodeResource(res, R.drawable.baby);
    babyImg = Bitmap.createScaledBitmap(babyImg, 640, 971, false);
  }

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  int getWidth() {
    return width;
  }

  int getHeight() {
    return height;
  }
}