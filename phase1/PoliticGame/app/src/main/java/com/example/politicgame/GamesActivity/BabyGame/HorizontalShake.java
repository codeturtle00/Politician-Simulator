package com.example.politicgame.GamesActivity.BabyGame;

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
    setX(determineXCoordinate());
    setY(determineYCoordinate());
    setImg(BitmapFactory.decodeResource(res, R.drawable.kisslips));
  }

  /**
   * Returns positive or negative change in happiness based on touch inputs
   *
   * @param v the View being used
   * @param initialX the X coordinate of the initial touch
   * @param initialY the Y coordinate of the initial touch
   * @param finalX the X coordinate of where the touch ended
   * @param finalY the Y coordinate of where the touch ended
   * @return value to change baby happiness by
   */
  @Override
  int handleTouch(View v, float initialX, float initialY, float finalX, float finalY) {
    if (initialX > getX() && initialY > getY()) {
      if (Math.abs(finalY - initialY) < 50 && Math.abs(finalX - initialX) > 100) {
        Log.d("HorizontalShake", "Score increased");
        return 1;
      }
    }
    Log.d("HorizontalShake", "Score decreased");
    return -1;
  }

  @Override
  int determineXCoordinate() {
    return 0;
  }

  @Override
  int determineYCoordinate() {
    return 0;
  }
}
