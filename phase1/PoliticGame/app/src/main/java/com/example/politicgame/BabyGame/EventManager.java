package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class EventManager implements View.OnTouchListener {
  private ArrayList<Event> events;
  private Resources babyResources;
  private float babyX;
  private float babyY;
  private float holderWidth;
  private float holderHeight;
  private float initialX;
  private float initialY;
  private float finalX;
  private float finalY;

  EventManager(float babyX, float babyY, int holderWidth, int holderHeight, Resources babyResources) {
    events = new ArrayList<>();
    this.babyResources = babyResources;
    this.babyX = babyX;
    this.babyY = babyY;
    this.holderWidth = holderWidth;
    this.holderHeight = holderHeight;
  }

  void randomEvent() {
    Random rand = new Random();
    final int randomNum = rand.nextInt((4) + 1) + 1;
    if (randomNum == 1) {
//        events.add(new Kiss(startX, startY, babyView.getResources()));
    } else if (randomNum == 2) {
        events.add(new HorizontalShake(babyX + holderWidth, babyY + holderHeight, babyResources));
    }
  }

  @Override
  public boolean onTouch(View v, MotionEvent touch) {
    // check if touched within 2 coordinates of an event
    for (Event event: events) {
      if (Math.abs(event.getX()-touch.getX()) < 2 &
              Math.abs(event.getY()-touch.getY()) < 2) {
        event.handleTouch(v, touch);
        return true;
      }
    }
    return false;
//    switch (event.getAction()) {
//      case MotionEvent.ACTION_DOWN:
//        initialX = event.getX();
//        initialY = event.getY();
//        return true;
//      case MotionEvent.ACTION_MOVE:
//        finalX = event.getX();
//        finalY = event.getY();
//        return true;
//      default:
//        return false;
//    }
  }

  void update(int timeLeft) {
      if (timeLeft % 5 == 0) {
          randomEvent();
          randomEvent();
      }
    for (Event event : events) {
      event.update();
    }
  }

  void draw(Canvas canvas) {
    for (Event event : events) {
      event.draw(canvas);
    }
  }
}
