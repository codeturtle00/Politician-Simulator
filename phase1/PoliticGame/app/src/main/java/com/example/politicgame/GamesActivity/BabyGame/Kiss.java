package com.example.politicgame.GamesActivity.BabyGame;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.politicgame.R;

class Kiss extends Event {

  Kiss(int babyX, int babyY, int babyWidth, int babyHeight, Resources res) {
    super(babyX, babyY, babyWidth, babyHeight, res);
    setX(determineXCoordinate());
    setY(determineYCoordinate());
    setImg(BitmapFactory.decodeResource(res, R.drawable.kisslips));
  }

  @Override
  int handleTouch(View v, float initialX, float initialY, float finalX, float finalY) {
      if (Math.abs(finalY - initialY) < 5 && Math.abs(finalX - initialX) < 5) {
        Log.d("Kiss", "Score increased");
        return 1;
      }
    Log.d("Kiss", "Score decreased");
    return -5;
  }

  @Override
  int determineXCoordinate() {
//    Random r = new Random();
//    return babyX + r.nextInt(babyWidth);
    return 0;
  }

  @Override
  int determineYCoordinate() {
//    Random r = new Random();
//    return babyY + r.nextInt(babyHeight / 2);
    return 0;
  }
}
