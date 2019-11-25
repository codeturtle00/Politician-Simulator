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
    if (events.size() < 1) {
      Random rand = new Random();
      //            final int randomNum = rand.nextInt(4); // Generates number between 0 and 3
      int randomNum = 3; // ALWAYS SETS EVENT TO KISS
      if (randomNum == 1) {
        HorizontalShake horizontalShake =
            new HorizontalShake(babyX, babyY, babyWidth, babyHeight, babyResources);
        events.add(horizontalShake);
//        events.add(new HorizontalShake(babyX, babyY, babyWidth, babyHeight, babyResources));
        viewUpdater.updateEventAction("Horizontal Shake");
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
      handleTouch(v);
    }
    return true;
  }

  /**
   * Makes the correct calls to events when screen is touched. Also updates the score based on these
   * calls.
   *
   * @param v the View currently used
   */
  void handleTouch(View v) {
    Random r = new Random();
    ArrayList<Event> tempEvents = new ArrayList<>(events);
    int totalScoreChange = 0;
    for (Event event : tempEvents) {
      // If click near an event, then call the event's handleTouch()
      if (Math.abs(event.getX() - initialX) < 200 && Math.abs(event.getY() - initialY) < 200) {
        int scoreChange = event.handleTouch(v, initialX, initialY, finalX, finalY);
        // randomize scoreChange between 0.5x to 1.5x
        scoreChange *= (0.5 + r.nextFloat());
        totalScoreChange += scoreChange;
      }

      // Removes kiss
      if (event.getClass().equals(Kiss.class) && event.getInteraction()) events.remove(event);
    }

    // If totalScoreChange is 0, user did not properly interact with any event.
    if (totalScoreChange == 0) {
      totalScoreChange = (int) (-10 * (0.5 + r.nextFloat()));
    }
    updateScore(totalScoreChange);
    update();
  }

  /** Updates the game. */
  void update() {
    viewUpdater.drawUpdate();
  }

  /**
   * Updates ViewUpdater rather than updating BabyView directly to prevent dependency on BabyView.
   *
   * @param happinessChange the amount to change happiness by
   */
  private void updateScore(int happinessChange) {
    viewUpdater.updateScore(happinessChange);
  }

  /**
   * Draws each event in events.
   *
   * @param canvas the canvas which to draw on
   */
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
