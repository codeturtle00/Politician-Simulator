package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

class EventManager implements View.OnTouchListener {
  /** List of all events currently going on. */
  private ArrayList<Event> events;

  /** Canvas to draw events. */
  private Canvas canvas;

  /** Resources for the baby. */
  private Resources babyResources;

  /** ViewUpdater object managed by this EventManager. */
  private ViewUpdater viewUpdater;

  /** X coordinate of the baby. */
  private int babyX;

  /** Y coordinate of the baby. */
  private int babyY;

  /** Width of the baby. */
  private int babyWidth;

  /** Height of the baby. */
  private int babyHeight;

  /** X coordinate when screen is touched. */
  private float initialX;

  /** Y coordinate when screen is touched. */
  private float initialY;

  /** X coordinate of where the touch ends; eg. end of a swipe. */
  private float finalX;

  /** Y coordinate of where the touch ends; eg. end of a swipe. */
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
  void randomEvent() {
    Log.d("Running random event", viewUpdater.toString());
    if (events.size() < 3) {
      Random rand = new Random();
//      final int randomNum = rand.nextInt(4); // Generates number between 0 and 3
      int randomNum = 3; // ALWAYS SETS EVENT TO KISS
      if (randomNum == 1) {
//        HorizontalShake horizontalShake =
//            new HorizontalShake(babyX, babyY, babyWidth, babyHeight, babyResources);
//        events.add(horizontalShake);
//        horizontalShake.draw(canvas);
        events.add(new HorizontalShake(babyX, babyY, babyWidth, babyHeight, babyResources));
        viewUpdater.updateEventAction(
                "The baby needs to be cradled! Swipe horizontally at any location.");
        Log.d("EventManager", "HorizontalShake started");
      } else if (randomNum == 2) {
//        VerticalShake verticalShake =
//            new VerticalShake(babyX, babyY, babyWidth, babyHeight, babyResources);
//        events.add(verticalShake);
//        verticalShake.draw(canvas);
        events.add(new VerticalShake(babyX, babyY, babyWidth, babyHeight, babyResources));
        viewUpdater.updateEventAction(
                "The baby needs to be cradled! Swipe vertically at any location.");
        Log.d("EventManager", "VerticalShake started");
      } else if (randomNum == 3) {
        Kiss kiss = new Kiss(babyX, babyY, babyWidth, babyHeight, babyResources);
        events.add(kiss);
//        events.add(new Kiss(babyX, babyY, babyWidth, babyHeight, babyResources));
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
      Random r = new Random();
      for (Event event : events) {
        int scoreChange = event.handleTouch(v, initialX, initialY, finalX, finalY);
        // randomize scoreChange between 0.5x to 1.5x
        scoreChange *= (0.5 + r.nextFloat());
        updateScore(scoreChange);
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

  void setCanvas(Canvas canvas) {
    this.canvas = canvas;
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

  /**
   * Sets width of the baby.
   *
   * @param width the width of the baby
   */
  void setBabyWidth(int width) {
    this.babyWidth = width;
  }

  /**
   * Sets height of the baby.
   *
   * @param height the height of the baby
   */
  void setBabyHeight(int height) {
    this.babyHeight = height;
  }
}