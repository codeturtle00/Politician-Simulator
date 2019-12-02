package com.example.politicgame.Games.BabyGame;

import android.util.Log;

import static java.lang.Thread.sleep;

public class EventsGenerator implements Runnable {

  private final EventManager eventManager;
  private final Object pauseLock;
  private boolean isRunning;

  EventsGenerator(EventManager eventManager) {
    this.eventManager = eventManager;
    isRunning = true;
    pauseLock = new Object();
  }

  @Override
  public void run() {
    while (isRunning) {

      if (eventManager.numEvents() < 3) eventManager.randomEvent();
      eventManager.update();

      try {
        sleep(500);
      } catch (Exception e) {
        e.printStackTrace();
        Log.e("EventsGenerator", "Exception");
      }

      synchronized (pauseLock) {
        while (!isRunning) {
          try {
            pauseLock.wait();
          } catch (InterruptedException e) {
          }
        }
      }
    }
  }

  public void pause() {
    synchronized (pauseLock) {
      isRunning = false;
    }
  }

  public void resume() {
    synchronized (pauseLock) {
      isRunning = true;
      pauseLock.notifyAll();
    }
  }
}
