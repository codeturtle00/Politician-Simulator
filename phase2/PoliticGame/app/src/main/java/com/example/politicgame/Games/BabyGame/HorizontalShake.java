package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.politicgame.R;

class HorizontalShake extends Event {
  private int swipesLeft = 3;
  private boolean moveLeft = true;

  /**
   * Creates this HorizontalShake event.
   *
   * @param babyX the X coordinate of the baby
   * @param babyY the Y coordinate of the baby
   * @param babyWidth the width of the baby
   * @param babyHeight the height of the baby
   * @param res the resources to draw the baby
   */
  HorizontalShake(int babyX, int babyY, int babyWidth, int babyHeight, Resources res) {
    super(babyX, babyY, babyWidth, babyHeight, res);
    Bitmap img = BitmapFactory.decodeResource(res, R.drawable.leftrightarrow);
    setImg(img);
    setX((int) (Math.random() * (babyWidth / 3) + babyX));
    setY((int) (Math.random() * (babyHeight / 2) + babyY + (babyHeight / 2)));
  }

  /**
   * Returns positive or negative change in happiness based on touch inputs.
   *
   * @param v the View being used
   * @param initialX the X coordinate of the initial touch
   * @param initialY the Y coordinate of the initial touch
   * @param movingX the updated X coordinate from finger movement
   * @param movingY the updated Y coordinate from finger movement
   * @param finalX not used
   * @param finalY not used
   * @return value to change baby happiness by
   */
  @Override
  int handleTouch(
      View v,
      float initialX,
      float initialY,
      float movingX,
      float movingY,
      float finalX,
      float finalY) {

    if (swipesLeft > 0 && Math.abs(movingY - getY()) < 50) {
      if (initialX - movingX > 100 && moveLeft) {
        swipesLeft--;
        Log.d("HorizontalShake", "Swiping Left, swipesLeft = " + swipesLeft);
        setImg(
            Bitmap.createScaledBitmap(
                getImg(), (int) (imgWidth() * 1.1), (int) (imgWidth() * 1.1), false));
        moveLeft = false;
        if (swipesLeft == 0) setInteraction(true);
        return 5;
      } else if (movingX - initialX > 100 && !moveLeft) {
        swipesLeft--;
        Log.d("HorizontalShake", "Swiping Right, swipesLeft = " + swipesLeft);
        setImg(
            Bitmap.createScaledBitmap(
                getImg(), (int) (imgWidth() * 1.1), (int) (imgWidth() * 1.1), false));
        moveLeft = true;
        if (swipesLeft == 0) setInteraction(true);
        return 5;
      }
    }
    return 0;
  }
}
