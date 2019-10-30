package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.view.View;

class HorizontalShake extends Event {

  HorizontalShake(float x, float y, Resources res) {
    super(x, y, res);
  }

  @Override
  int update() {
    return getDeltaScore();
  }

  @Override
  void handleTouch(View v, float initialX, float initialY, float finalX, float finalY) {
    if (initialX > getX() && initialY > getY()) {
      if (Math.abs(finalY - initialY) < 20) {
        setDeltaScore(1);
      } else {
        setDeltaScore(-1);
      }
    }
  }
}
