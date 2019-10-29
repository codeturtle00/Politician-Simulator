package com.example.politicgame.BabyGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BabyView extends SurfaceView implements Runnable {
  private SurfaceHolder holder;
  private Canvas canvas;
  private boolean isRunning;
  private Thread thread;

  public BabyView(Context context) {
    super(context);
    isRunning = true;
    holder = getHolder();
    holder.addCallback(
        new SurfaceHolder.Callback() {

          @Override
          public void surfaceDestroyed(SurfaceHolder holder) {}

          @Override
          public void surfaceCreated(SurfaceHolder holder) {
            canvas = holder.lockCanvas();
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
    canvas.drawColor(Color.rgb(0, 188, 212));
    Paint baby = new Paint();
    baby.setColor(Color.WHITE);
    canvas.drawCircle(
        holder.getSurfaceFrame().width() / 2, holder.getSurfaceFrame().height() / 2, 400, baby);
    System.out.println("drew circle");
  }

  public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();
    return true;
  }

  @Override
  public void run() {
    while (isRunning) {
      update();
      draw(canvas);
      sleep();
    }
  }

  private void sleep() {
    try {
      Thread.sleep(17);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void update() {}

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
