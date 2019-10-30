package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;

class HorizontalShake extends Event {

  HorizontalShake(float x, float y, Resources res) {
    super(x, y, res);
  }

  @Override
  void update() {}

  @Override
  void handleTouch(View v, MotionEvent event) {

  }
}
