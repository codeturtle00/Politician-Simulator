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
  private ViewUpdater viewUpdater;

  /** X coordinate of the baby */
  // DOESN'T WORK YET
  private int babyX;

  /** Y coordinate of the baby */
  // DOESN'T WORK YET
  private int babyY;

  /** Width of the baby */
  // DOESN'T WORK YET
  private int babyWidth = 0;

  /** Height of the baby */
  // DOESN'T WORK YET
  private int babyHeight = 0;

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
   *
   * @param babyResources resources needed to draw the baby
   * @param viewUpdater ViewUpdater this EventManager updates.
   */
  EventManager(Resources babyResources, ViewUpdater viewUpdater) {
    events = new ArrayList<>();
    this.babyResources = babyResources;
    this.viewUpdater = viewUpdater;
  }

  /** Randomly generates an event. */
  void randomEvent(int timeLeft) {
    if (timeLeft % 3 == 0) {
      events.clear();
      Random rand = new Random();
      final int randomNum = rand.nextInt(4); // Generates number between 0 and 3
      if (randomNum == 1) {
        events.add(new HorizontalShake(babyX, babyY, babyWidth, babyHeight, babyResources));
        viewUpdater.updateEventAction(
            "The baby needs to be cradled! Swipe horizontally at any location.");
        Log.d("EventManager", "HorizontalShake started");
        System.out.println("Horizontal Event Set!");
      } else if (randomNum == 2) {
        events.add(new VerticalShake(babyX, babyY, babyWidth, babyHeight, babyResources));
        viewUpdater.updateEventAction(
            "The baby needs to be cradled! Swipe vertically at any location.");
        System.out.println("Vertical Event Set!");
        Log.d("EventManager", "VerticalShake started");
      } else if (randomNum == 3) {
        events.add(new Kiss(babyX, babyY, babyWidth, babyHeight, babyResources));
        viewUpdater.updateEventAction("Kiss the baby. Touch anywhere");
        Log.d("EventManager", "Kiss started");
      }
    }
  }

  /**
   * Calls events when screen is touched to handleTouch the score.
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
        updateScore(e.handleTouch(v, initialX, initialY, finalX, finalY));
      }
    }
    return true;
  }

  /**
   * Updates ViewUpdater rather than updating BabyView directly to prevent dependency on BabyView.
   *
   * @param happinessChange the amount to change happiness by
   */
  private void updateScore(int happinessChange) {
    viewUpdater.updateScore(happinessChange);
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
  void setBabyX(int babyX) {
    this.babyX = babyX;
  }

  /**
   * Sets Y coordinate of the baby.
   *
   * @param babyY Y coordinate of the baby
   */
  void setBabyY(int babyY) {
    this.babyY = babyY;
  }
}
