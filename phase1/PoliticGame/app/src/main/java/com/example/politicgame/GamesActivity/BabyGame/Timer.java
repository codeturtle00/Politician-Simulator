package com.example.politicgame.GamesActivity.BabyGame;

import android.os.CountDownTimer;

class Timer {
  /** Time left. */
  private long timeLeftInMillis;

  /** Countdown timer */
  private CountDownTimer timer;

  /** This Timer's BabyDraw. */
  private BabyDraw babyDraw;

  /** This Timer's BabyView. */
  private BabyView babyView;

  /**
   * Creates a new timer.
   *
   * @param babyDraw this Timer's BabyDraw
   * @param babyView this Timer's BabyView
   */
  Timer(BabyDraw babyDraw, BabyView babyView) {
    timeLeftInMillis = 30000;
    this.babyDraw = babyDraw;
    this.babyView = babyView;
  }

  /** Cancels current timer object */
  void pause() {
    if (timer != null) {
      timer.cancel();
      timer = null;
    }
  }

  /** Resumes timer with however much time the player had left before. */
  void resume() {
    System.out.println("resuming with time " + timeLeftInMillis);
    timer =
        new CountDownTimer(timeLeftInMillis, 1000) {

          public void onTick(long millisUntilFinished) {
            timeLeftInMillis = millisUntilFinished;
            Integer timeLeft = (int) timeLeftInMillis / 1000;
            System.out.println(timeLeft);
            babyDraw.updateTime(timeLeft.toString(), false);
          }

          public void onFinish() {
            babyDraw.updateTime("Time's up!", true);
          }
        };
    timer.start();
  }
}
