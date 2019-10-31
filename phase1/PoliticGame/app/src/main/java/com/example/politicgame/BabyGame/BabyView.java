package com.example.politicgame.BabyGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class BabyView extends SurfaceView implements Runnable {
  private boolean isRunning;
  private Integer timeLeft;
  private BabyDraw babyDraw;
  private EventManager eventManager;

  private int holderWidth;
  private int holderHeight;
  private Canvas canvas;

  private Thread thread;

  BabyView(Context context) {
    super(context);
    isRunning = true;
    eventManager = new EventManager(getResources());
    setOnTouchListener(eventManager);
    SurfaceHolder holder = getHolder();
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
            }
          }

          @Override
          public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
        });
  }

  public void draw(Canvas canvas) {
    super.draw(canvas);

    // Set background color.
    canvas.drawColor(Color.rgb(0, 188, 212));
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

  Integer getTimeLeft() {
    return 0;
  }

  @Override
  public void run() {
    //    for (int i = 61; i > 0; i--) {
    //    if (isRunning) {
    //    update();
    //      eventManager.draw(canvas);
    //        sleep();
    //        timeLeft = i;
    //    }
    //    }
  }

  private void sleep() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void update() {
    babyDraw.updateScore(eventManager.update(0));
  }

  public void resume() {
    isRunning = true;
    thread = new Thread(this);
    thread.start();
  }

  public void pause() {
    try {
      isRunning = false;
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  void setBabyDraw(BabyDraw babyDraw) {
    this.babyDraw = babyDraw;
  }
}
