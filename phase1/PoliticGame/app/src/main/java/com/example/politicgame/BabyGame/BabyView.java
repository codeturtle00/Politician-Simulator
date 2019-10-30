package com.example.politicgame.BabyGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BabyView extends SurfaceView implements Runnable {
  private boolean isRunning;
  private Integer timeLeft;
  private EventManager eventManager;

  private SurfaceHolder holder;
  private int holderWidth;
  private int holderHeight;
  private Canvas canvas;

  private Thread thread;

  public BabyView(Context context) {
    super(context);
    isRunning = true;
    eventManager = new EventManager(getX(), getY(), holderWidth, holderHeight, getResources());
    holder = getHolder();
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

    // set background color
    canvas.drawColor(Color.rgb(0, 188, 212));
    Paint paint = new Paint();
    paint.setColor(Color.WHITE);

    // example circle
    canvas.drawCircle(holderWidth / 2, holderHeight / 2, 400, paint);
    System.out.println("drew circle");

    // trying to draw baby in centre. pls help!
    Baby baby = new Baby(holderWidth / 2, holderHeight / 2, getResources());
    baby.draw(canvas);
  }

  Integer getTimeLeft() {
    return 0;
  }

  @Override
  public void run() {
//    for (int i = 61; i > 0; i--) {
      if (isRunning) {
        update();
        eventManager.draw(canvas);
        sleep();
//        timeLeft = i;
      }
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
    eventManager.update(timeLeft);
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
}
