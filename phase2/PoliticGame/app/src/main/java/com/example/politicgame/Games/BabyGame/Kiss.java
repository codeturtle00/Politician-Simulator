package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.politicgame.R;

class Kiss extends Event {
  /**
   * Creates this Kiss event.
   *
   * @param babyX the X coordinate of the baby
   * @param babyY the Y coordinate of the baby
   * @param babyWidth the width of the baby
   * @param babyHeight the height of the baby
   * @param res the resources to draw the baby
   */
  Kiss(int babyX, int babyY, int babyWidth, int babyHeight, Resources res) {
    super(babyX, babyY, babyWidth, babyHeight, res);
    Bitmap kiss = BitmapFactory.decodeResource(res, R.drawable.kisslips);
    kiss = Bitmap.createScaledBitmap(kiss, 100, 60, false);
    setImg(kiss);
    setX((int) (Math.random() * (babyWidth / 2) + babyX));
    setY((int) (Math.random() * (babyHeight / 2) + babyY));
  }

  /**
   * Returns positive or negative change in happiness based on touch inputs.
   *
   * @param v the View being used
   * @param initialX the X coordinate of the initial touch
   * @param initialY the Y coordinate of the initial touch
   * @param movingX not used
   * @param movingY not used
   * @param finalX the X coordinate of where the touch ended
   * @param finalY the Y coordinate of where the touch ended
   * @return value to change baby happiness by
   */
  @Override
  int handleTouch(View v, float initialX, float initialY, float movingX, float movingY, float finalX, float finalY) {
    if (Math.abs(finalX - initialX) < 20 && Math.abs(finalY - initialY) < 20) {
      if (!getInteraction()
          && -20 < finalX - getX()
          && finalX - getX() < imgWidth() + 20
          && -20 < finalY - getY()
          && finalY - getY() < imgHeight() + 20) {
        Log.d("Kiss", "Score increased");
        setInteraction(true);
        return 10;
      }
    }
    return 0;
  }
}
