package com.example.politicgame.Games.BabyGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ConcurrentModificationException;

class BabyDefaultView extends SurfaceView implements DefaultView {

  /**
   * The DefaultBGActivity stored in this BabyDefaultView, using dependency injection to call methods in
   * BabyActivity.
   */
  private DefaultBGActivity defaultBGActivity;

  /** The EventManager stored in this BabyDefaultView. */
  private EventManager eventManager;

  /** Holder width. */
  private int holderWidth;

  /** Holder Height */
  private int holderHeight;

  /** Canvas which is being drawn on */
  private Canvas canvas;

  private EventsGenerator eventsGenerator;

  private Baby baby;

  /**
   * Creates the BabyDefaultView object which tells BabyActivity what animations to draw, scores to update,
   * times to update, and instructions to display.
   *
   * @param context the surface context
   */
  BabyDefaultView(Context context) {
    super(context);

    this.defaultBGActivity = (DefaultBGActivity) context;

    // EventManager will manage the events for this game.
    eventManager = new EventManager(getResources(), this);
    setOnTouchListener(eventManager);

    SurfaceHolder holder = getHolder();
    setZOrderOnTop(true);
    holder.setFormat(PixelFormat.TRANSPARENT);

    holder.addCallback(
        new SurfaceHolder.Callback() {
          @Override
          public void surfaceDestroyed(SurfaceHolder holder) {}

          @Override
          public void surfaceCreated(SurfaceHolder holder) {
            canvas = holder.lockCanvas();
            holderWidth = holder.getSurfaceFrame().width();
            holderHeight = holder.getSurfaceFrame().height();
            baby = new Baby(holderWidth / 2, holderHeight / 2, getResources(), (int)(holderWidth * 0.8));

            // Set the baby in the eventManager.
            System.out.println(baby);
            eventManager.setBaby(baby);

            if (canvas != null) {
              draw(canvas);
              holder.unlockCanvasAndPost(canvas);

              // Create EventsGenerator
              eventsGenerator = new EventsGenerator(eventManager);
              Thread thread = new Thread(eventsGenerator);
              thread.start();
            }
          }

          @Override
          public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
        });
  }

  /**
   * Draws the initial state of the activity on a canvas.
   *
   * @param canvas the canvas which is drawn on.
   */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    canvas.drawColor(0, PorterDuff.Mode.CLEAR);

    Paint paint = new Paint();
    paint.setColor(Color.WHITE);

    // Example circle.
    canvas.drawCircle(holderWidth / 2, holderHeight / 2, 400, paint);
    System.out.println("drew circle");

    // Draws baby in the center.
    baby.draw(canvas);

    try {
      eventManager.draw(canvas);
    } catch (ConcurrentModificationException e) {
      Log.e("Babyview draw()", "ConcurrentModificationException");
    }
  }

  /**
   * Sets the image of the baby based on happiness.
   *
   * @param happiness the happiness of the baby
   */
  void setBabyMood(int happiness) {
    if (happiness <= 20) {
      baby.setCry(getResources());
    } else if (happiness <= 80) {
      baby.setSad(getResources());
    } else {
      baby.setHappy(getResources());
    }
  }

  /** Draws baby and events. */
  @Override
  public void drawUpdate() {
    canvas = getHolder().lockCanvas();
    if (canvas != null) draw(canvas);
    getHolder().unlockCanvasAndPost(canvas);
  }

  /**
   * Updates DefaultBGActivity rather than directly updating BabyActivity to prevent dependency on
   * BabyActivity.
   *
   * @param happinessChange the amount to change happiness by
   */
  @Override
  public void updateScore(int happinessChange) {
    defaultBGActivity.updateScore(happinessChange);
  }

  /**
   * Updates the event action to be performed.
   *
   * @param eventAction the action to be performed
   */
  @Override
  public void updateEventAction(String eventAction) {
    System.out.println("arrived in babyView with string" + eventAction);
    System.out.println(defaultBGActivity);
    defaultBGActivity.updateEventAction(eventAction);
    drawUpdate();
  }

  /**
   * Sets the DefaultBGActivity for this BabyDefaultView.
   *
   * @param defaultBGActivity the DefaultBGActivity
   */
  void setDefaultBGActivity(DefaultBGActivity defaultBGActivity) {
    this.defaultBGActivity = defaultBGActivity;
  }

  void pause() {
    if (eventsGenerator != null) {
      eventsGenerator.pause();
    }
  }

  public void resume() {
    if (eventsGenerator != null) {
      eventsGenerator.resume();
    }
  }
}
