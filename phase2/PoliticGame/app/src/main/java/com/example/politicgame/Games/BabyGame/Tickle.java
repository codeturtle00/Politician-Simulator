package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.politicgame.R;

class Tickle extends Event {
  private int numTickles = 5;
  private int left;
  private int right;

  /**
   * Creates this Tickle event.
   *
   * @param baby The Baby this event is acting upon
   * @param res the resources to draw the baby
   */
  Tickle(Baby baby, Resources res) {
    super(baby, res);
    Bitmap tickle = BitmapFactory.decodeResource(res, R.drawable.tickle);
    tickle = Bitmap.createScaledBitmap(tickle, 180, 180, false);
    setImg(tickle);
    left = getBabyX() + (getBabyWidth() / 5);
    right = getBabyX() + getBabyWidth() - (getBabyWidth() / 5);
    int side = Math.random() < 0.5 ? 0 : 1;
    if (side == 0) setX(left);
    else setX(right);
    setY((getBabyHeight() / 3) + getBabyY());
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
  int handleTouch(
      View v,
      float initialX,
      float initialY,
      float movingX,
      float movingY,
      float finalX,
      float finalY) {

    if (numTickles > 0 && Math.abs(finalX - initialX) < 20 && Math.abs(finalY - initialY) < 20) {
      if (!getInteraction()
          && -20 < finalX - getX()
          && finalX - getX() < imgWidth() + 20
          && -20 < finalY - getY()
          && finalY - getY() < imgHeight() + 20) {
        numTickles--;
        if (getX() == left) {
          setX(right);
          Log.d("Tickle", "Right Side Set");
        } else {
          setX(left);
          Log.d("Tickle", "Left Side Set");
        }
        if (numTickles == 0) setInteraction(true);
        return 3;
      }
    }
    return 0;
  }
}
