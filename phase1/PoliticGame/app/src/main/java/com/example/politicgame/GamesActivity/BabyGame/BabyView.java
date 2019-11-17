package com.example.politicgame.GamesActivity.BabyGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class BabyView extends SurfaceView implements ViewUpdater {

  /**
   * The BabyDraw stored in this BabyView, using dependency injection to call methods in
   * BabyActivity.
   */
  private BabyDraw babyDraw;

  /** The EventManager stored in this BabyView. */
  private EventManager eventManager;

  /** Holder width. */
  private int holderWidth;

  /** Holder Height */
  private int holderHeight;

  /** Canvas which is being drawn on */
  private Canvas canvas;

  private EventsGenerator eventsGenerator;

  /**
   * Creates the BabyView object which tells BabyActivity what animations to draw, scores to update,
   * times to update, and instructions to display.
   *
   * @param context the surface context
   */
  BabyView(Context context) {
    super(context);

    // EventManager will manage the events for this game.
    eventManager = new EventManager(getResources(), this);
    setOnTouchListener(eventManager);

    this.babyDraw = (BabyDraw) context;

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

    Paint paint = new Paint();
    paint.setColor(Color.WHITE);

    // Example circle.
    canvas.drawCircle(holderWidth / 2, holderHeight / 2, 400, paint);
    System.out.println("drew circle");

    // Draws baby in the center.
    Baby baby = new Baby(holderWidth / 2, holderHeight / 2, getResources());
    baby.draw(canvas);

    // Set the baby's coordinates in the eventManager.
    eventManager.setBabyX(holderWidth / 2);
    eventManager.setBabyY(holderHeight / 2);
  }

  /**
   * Updates BabyDraw rather than directly updating BabyActivity to prevent dependency on
   * BabyActivity.
   *
   * @param happinessChange the amount to change happiness by
   */
  @Override
  public void updateScore(int happinessChange) {
    babyDraw.updateScore(happinessChange);
  }

  /**
   * Updates the event action to be performed.
   *
   * @param eventAction the action to be performed
   */
  @Override
  public void updateEventAction(String eventAction) {
    System.out.println("arrived in babyView with string" + eventAction);
    System.out.println(babyDraw);
    babyDraw.updateEventAction(eventAction);
  }

  /**
   * Sets the BabyDraw for this BabyView.
   *
   * @param babyDraw the BabyDraw
   */
  void setBabyDraw(BabyDraw babyDraw) {
    this.babyDraw = babyDraw;
  }

  void pause() {
    if (eventsGenerator != null) eventsGenerator.setRunning(false);
  }

  public void resume() {
    if (eventsGenerator != null) eventsGenerator.setRunning(true);
  }
}
