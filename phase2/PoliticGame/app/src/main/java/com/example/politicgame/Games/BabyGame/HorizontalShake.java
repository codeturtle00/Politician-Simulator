package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.politicgame.R;

class HorizontalShake extends Event {
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
    setImg(BitmapFactory.decodeResource(res, R.drawable.leftrightarrow));
    setX((int) (Math.random() * (babyWidth / 2) + babyX + (babyWidth / 2)));
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
    if (initialX > getX() && initialY > getY()) {
      if (Math.abs(finalY - initialY) < 200 && Math.abs(finalX - initialX) > 200) {
        Log.d("HorizontalShake", "Score increased");
        setInteraction(true);
        return 5;
      }
    }
    Log.d("HorizontalShake", "Score decreased");
    return -5;
  }
}