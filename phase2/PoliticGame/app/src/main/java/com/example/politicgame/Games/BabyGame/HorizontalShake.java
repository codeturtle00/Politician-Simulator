package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.politicgame.R;

class HorizontalShake extends Event {

    private int swipesLeft = 5;
    private boolean moveLeft = true;
    private Bitmap img;

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
    img = BitmapFactory.decodeResource(res, R.drawable.leftrightarrow);
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
   * @param finalX the X coordinate of where the touch ended
   * @param finalY the Y coordinate of where the touch ended
   * @return value to change baby happiness by
   */
  @Override
  int handleTouch(View v, float initialX, float initialY, float movingX, float movingY, float finalX, float finalY) {

      if (swipesLeft > 0 && Math.abs(initialY - movingY) < 50) {
          if (initialX - movingX > 100 && moveLeft) {
            swipesLeft--;
            Log.d("HorizontalShake", "Swiping Left, swipesLeft = " + swipesLeft);
            img = Bitmap.createScaledBitmap(img, (int) (imgWidth() * 1.1), (int) (imgWidth() * 1.1), false);
            setImg(img);
            moveLeft = false;
          } else if (movingX - initialX > 100 && !moveLeft) {
            swipesLeft--;
            Log.d("HorizontalShake", "Swiping Right, swipesLeft = " + swipesLeft);
            img = Bitmap.createScaledBitmap(img, (int) (imgWidth() * 1.1), (int) (imgWidth() * 1.1), false);
            setImg(img);
            moveLeft = true;
          }
      }
      if (swipesLeft == 0) {
          setInteraction(true);
          return 10;
      }
      return 0;
  }
}