package com.example.politicgame.BabyGame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

class EventManager implements View.OnTouchListener {
  /** List of all events currently going on */
  private ArrayList<Event> events;

  /** Resources for the baby */
  private Resources babyResources;

  /** ViewUpdater object managed by this EventManager */
  private BabyView view;

  /** X coordinate of the baby */
  // DOESN'T WORK YET
  private float babyX;

  /** Y coordinate of the baby */
  // DOESN'T WORK YET
  private float babyY;

  /** X coordinate when screen is touched */
  private float initialX;

  /** Y coordinate when screen is touched */
  private float initialY;

  /** X coordinate of where the touch ends; eg. end of a swipe */
  private float finalX;

  /** Y coordinate of where the touch ends; eg. end of a swipe */
  private float finalY;

  /**
   * Initializes a new EventManager which manages screen touches and events.
   *  @param babyResources resources needed to draw the baby
   * @param view ViewUpdater this EventManager updates.
   */
  EventManager(Resources babyResources, BabyView view) {
    events = new ArrayList<>();
    this.babyResources = babyResources;
    this.view = view;

//    events.add(new HorizontalShake(this.babyX, this.babyY, this.babyResources));
  }

  /** Randomly generates an event. */
  void randomEvent() {
    Random rand = new Random();
    final int randomNum = rand.nextInt((4) + 1) + 1;
  }

  /**
   * Calls events when screen is touched to update the score.
   *
   * @param v View that is being used
   * @param touch the touch to the screen
   * @return true
   */
  @Override
  public boolean onTouch(View v, MotionEvent touch) {
    switch (touch.getAction()) {
      case MotionEvent.ACTION_DOWN: // Screen was initially touched
        initialX = touch.getX();
        initialY = touch.getY();
        Log.d("EventManager", "ACTION_DOWN registered");
        break;
      case MotionEvent.ACTION_UP: // When finger is lifted off screen; eg. end of a swipe
        finalX = touch.getX();
        finalY = touch.getY();
        Log.d("EventManager", "ACTION_UP registered");
        break;
      default:
        return false;
    }
    // Only runs when finger is lifted off screen
    if (touch.getAction() == MotionEvent.ACTION_UP) {
      for (Event e : events) {
        update(e.update(v, initialX, initialY, finalX, finalY));
      }
      update(0);
    }
    return true;
  }

  /**
   * Updates ViewUpdater rather than updating BabyView directly to prevent dependency on BabyView.
   *
   * @param happinessChange the amount to change happiness by
   */
  void update(int happinessChange) {
    view.update(happinessChange);
  }

  // NOT USED YET
  void draw(Canvas canvas) {
    for (Event event : events) {
      event.draw(canvas);
    }
  }

  /**
   * Sets X coordinate of the baby.
   *
   * @param babyX X coordinate of the baby
   */
  void setBabyX(float babyX) {
    this.babyX = babyX;
  }

  /**
   * Sets Y coordinate of the baby.
   *
   * @param babyY Y coordinate of the baby
   */
  void setBabyY(float babyY) {
    this.babyY = babyY;
  }
}
