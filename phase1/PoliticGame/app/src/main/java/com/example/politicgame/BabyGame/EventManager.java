package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

class EventManager implements View.OnTouchListener {
  private ArrayList<Event> events;
  private Resources babyResources;
  private float babyX;
  private float babyY;
  private float initialX;
  private float initialY;
  private float finalX;
  private float finalY;

  EventManager(Resources babyResources) {
    events = new ArrayList<>();
    this.babyResources = babyResources;

    events.add(new HorizontalShake(this.babyX, this.babyY, this.babyResources));
  }

  void randomEvent() {
    Random rand = new Random();
    final int randomNum = rand.nextInt((4) + 1) + 1;
  }

  @Override
  public boolean onTouch(View v, MotionEvent touch) {
    // check if touched within 2 coordinates of an event
//    for (Event event: events) {
//      if (Math.abs(event.getX()-touch.getX()) < 2 &
//              Math.abs(event.getY()-touch.getY()) < 2) {
//        event.handleTouch(v, touch);
//        return true;
//      }
//    }
//    return false;
    switch (touch.getAction()) {
      case MotionEvent.ACTION_DOWN:
        initialX = touch.getX();
        initialY = touch.getY();
//        break;
      case MotionEvent.ACTION_MOVE:
        finalX = touch.getX();
        finalY = touch.getY();
        break;
      default:
        return false;
    }
    for (Event e : events) {
      e.handleTouch(v, initialX, initialY, finalX, finalY);
    }
    return true;
  }

  int update(int timeLeft) {
      if (timeLeft % 5 == 0) {
          randomEvent();
          randomEvent();
      }
      int sum = 0;
    for (Event event : events) {
      sum += (event.update());
    }
    return sum;
  }

  void draw(Canvas canvas) {
    for (Event event : events) {
      event.draw(canvas);
    }
  }

  void setBabyX(float babyX) {
    this.babyX = babyX;
  }

  void setBabyY(float babyY) {
    this.babyY = babyY;
  }
}
