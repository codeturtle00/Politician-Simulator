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
  private Bitmap happyBabyImg;
  private Bitmap sadBabyImg;
  private Bitmap cryBabyImg;
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

    // Initiates all forms of baby
    happyBabyImg = BitmapFactory.decodeResource(res, R.drawable.baby);
    happyBabyImg = Bitmap.createScaledBitmap(happyBabyImg, width, height, false);

    sadBabyImg = BitmapFactory.decodeResource(res, R.drawable.sadbaby);
    sadBabyImg = Bitmap.createScaledBitmap(sadBabyImg, width, height, false);

    cryBabyImg = BitmapFactory.decodeResource(res, R.drawable.crybaby);
    cryBabyImg = Bitmap.createScaledBitmap(cryBabyImg, width, height, false);

    //default babyImg is happy
    babyImg = happyBabyImg;

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
   */
  void setCry() {
    babyImg = cryBabyImg;
  }

  /**
   * Sets this Baby to be sad.
   */
  void setSad() {
    babyImg = sadBabyImg;
  }

  /**
   * Sets this Baby to be happy.
   */
  void setHappy() {
    babyImg = happyBabyImg;
  }

  /** Returns the original X coordinate of the baby. */
  int getX() {
    return this.x;
  }

  /** Returns the original Y coordinate of the baby. */
  int getY() {
    return this.y;
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
