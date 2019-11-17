package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.politicgame.R;

class Baby {
  /** This Baby's X coordinate. */
  private float x;

  /** This Baby's Y coordinate. */
  private float y;

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
   * @param x the X coordinate of the Baby
   * @param y the Y coordinate of the Baby
   * @param res the resources used to draw this Baby
   */
  Baby(int x, int y, Resources res) {
    paint = new Paint();
    this.x = x;
    this.y = y;
    babyImg = BitmapFactory.decodeResource(res, R.drawable.baby);
    // width and height needs to be changed to dynamically scaled depending on holder width/height
    babyImg = Bitmap.createScaledBitmap(babyImg, 640, 1280, false);
    this.width = babyImg.getWidth();
    this.height = babyImg.getHeight();
  }

  /**
   * Draws this Baby object.
   *
   * @param canvas the canvas to draw the Baby on
   */
  void draw(Canvas canvas) {
    canvas.drawBitmap(babyImg, this.x - (width / 2), this.y - (height / 2), paint);
    System.out.println("Drew baby");
  }
}
