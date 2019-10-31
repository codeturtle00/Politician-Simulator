package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.politicgame.R;

import java.util.Random;

class Kiss extends Event {


  Kiss(int babyX, int babyY, int babyWidth, int babyHeight, Resources res) {
    super(babyX, babyY, babyWidth, babyHeight, res);
    setX(determineXCoordinate());
    setY(determineYCoordinate());
    setImg(BitmapFactory.decodeResource(res, R.drawable.kisslips));
  }

  @Override
  int update(View v, float initialX, float initialY, float finalX, float finalY) {
    return 0; // Implement this class
  }

  @Override
  int determineXCoordinate() {
    Random r = new Random();
    return babyX + r.nextInt(babyWidth);
  }

  @Override
  int determineYCoordinate() {
    Random r = new Random();
    return babyY + r.nextInt(babyHeight/2);
  }
}
