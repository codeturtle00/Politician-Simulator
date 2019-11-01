package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.politicgame.R;

public class Baby {

  private float x;
  private float y;
  private int width;
  private int height;
  private Paint paint;
  private Bitmap babyImg;

  public Baby(int x, int y, Resources res) {
    paint = new Paint();
    this.x = x;
    this.y = y;
    babyImg = BitmapFactory.decodeResource(res, R.drawable.baby);
    // width and height needs to be changed to dynamically scaled depending on holder width/height
    babyImg = Bitmap.createScaledBitmap(babyImg, 640, 1280, false);
    this.width = babyImg.getWidth();
    this.height = babyImg.getHeight();
  }

  public void draw(Canvas canvas) {
    canvas.drawBitmap(babyImg, this.x - (width / 2), this.y - (height / 2), paint);
    System.out.println("Drew baby");
  }
}
