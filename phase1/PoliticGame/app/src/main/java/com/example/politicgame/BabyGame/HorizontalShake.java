package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

class HorizontalShake extends Event {

  /**
   * Creates this HorizontalShake event.
   *
   * @param x the X coordinate of the baby
   * @param y the Y coordinate of the baby
   * @param res the resources to draw the baby
   */
  HorizontalShake(float x, float y, Resources res) {
    super(x, y, res);
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
  int update(View v, float initialX, float initialY, float finalX, float finalY) {
    if (initialX > getX() && initialY > getY()) {
      if (Math.abs(finalY - initialY) < 50 && Math.abs(finalX - initialX) > 100) {
        Log.d("HorizontalShake", "Score increased");
        return 1;
      }
    }
    Log.d("HorizontalShake", "Score decreased");
    return -1;
  }
}
