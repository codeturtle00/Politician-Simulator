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

  /** DefaultView object managed by this EventManager. */
  private DefaultView defaultView;

  /** This game's Baby */
  private Baby baby;

  private float initialX;
  private float initialY;
  private float movingX;
  private float movingY;
  private boolean moving;

  /**
   * Initializes a new EventManager which manages screen touches and events.
   *
   * @param babyResources resources needed to draw the baby
   * @param defaultView DefaultView this EventManager updates.
   */
  EventManager(Resources babyResources, DefaultView defaultView) {
    events = new ArrayList<>();
    this.babyResources = babyResources;
    this.defaultView = defaultView;
  }

  /** Randomly generates an event. */
  void randomEvent() {
    Log.d("Running random event", defaultView.toString());
    Random rand = new Random();
    final int randomNum = rand.nextInt(7); // Generates number between 0 and 5

    // Only 1-4 will trigger an event
    if (randomNum == 1) {
      HorizontalShake horizontalShake = new HorizontalShake(baby, babyResources);
      events.add(horizontalShake);
      Log.d("EventManager", "HorizontalShake started");

    } else if (randomNum == 2) {
      VerticalShake verticalShake = new VerticalShake(baby, babyResources);
      events.add(verticalShake);
      Log.d("EventManager", "VerticalShake started");

    } else if (randomNum == 3) {
      Kiss kiss = new Kiss(baby, babyResources);
      events.add(kiss);
      Log.d("EventManager", "Kiss started");
    } else if (randomNum == 4) {
      Tickle tickle = new Tickle(baby, babyResources);
      events.add(tickle);
      Log.d("EventManager", "Tickle started");
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
        Log.d("EventManager", "ACTION_DOWN registered at " + initialX + ", " + initialY);
        break;

      case MotionEvent.ACTION_MOVE: // Finger moves
        movingX = touch.getX();
        movingY = touch.getY();
        moving = true;
        Log.d("EventManager", "ACTION_MOVE registered at " + movingX + ", " + movingY);
        handleTouch(v, 0, 0);
        break;

      case MotionEvent.ACTION_UP: // When finger is lifted off screen; eg. end of a swipe
        float finalX = touch.getX();
        float finalY = touch.getY();
        moving = false;
        Log.d("EventManager", "ACTION_UP registered at " + finalX + ", " + finalY);
        update();
        handleTouch(v, finalX, finalY);
        break;

      default:
        return false;
    }
    return true;
  }

  /**
   * Makes the correct calls to events when screen is touched. Also updates the score based on these
   * calls.
   *
   * @param v the View currently used
   */
  private void handleTouch(View v, float finalX, float finalY) {
    Random r = new Random();
    ArrayList<Event> tempEvents = new ArrayList<>(events);
    int totalScoreChange = 0;
    for (Event event : tempEvents) {
      // If click near an event, then call the event's handleTouch()
      if (Math.abs(event.getX() - initialX) < 200 && Math.abs(event.getY() - initialY) < 200) {
        int scoreChange =
            event.handleTouch(v, initialX, initialY, movingX, movingY, finalX, finalY);
        // randomize scoreChange between 0.5x to 1.5x
        scoreChange *= (0.5 + r.nextFloat());
        totalScoreChange += scoreChange;
      }

      // Removes events that are over.
      if (event.getInteraction()) events.remove(event);
    }

    // If totalScoreChange is 0 and finger stopped moving,
    // then user did not properly interact with any event.
    if (totalScoreChange == 0 && !moving) {
      totalScoreChange = (int) (-10 * (0.5 + r.nextFloat()));
    }
    updateScore(totalScoreChange);
    update();
  }

  /** Updates the game. */
  void update() {
    defaultView.drawUpdate();
  }

  /**
   * Updates DefaultView rather than updating BabyDefaultView directly to prevent dependency on
   * BabyDefaultView.
   *
   * @param happinessChange the amount to change happiness by
   */
  private void updateScore(int happinessChange) {
    defaultView.updateScore(happinessChange);
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
   * Sets the Baby.
   *
   * @param baby the Baby to be set to this EventManager
   */
  void setBaby(Baby baby) {
    this.baby = baby;
  }

  /**
   * Returns the number of events currently happening.
   *
   * @return the number of events going on
   */
  int numEvents() {
    return events.size();
  }
}
