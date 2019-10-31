package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.politicgame.R;

class Kiss extends Event {

  Kiss(float x, float y, Resources res) {
    super(x, y, res);
  }

  @Override
  int update(View v, float initialX, float initialY, float finalX, float finalY) {
    return 0; // Implement this class
  }
}
