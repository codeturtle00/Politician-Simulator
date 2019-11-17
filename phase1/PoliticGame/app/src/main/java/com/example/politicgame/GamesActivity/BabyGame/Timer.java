package com.example.politicgame.GamesActivity.BabyGame;

import android.os.CountDownTimer;

class Timer {
  /** Time left. */
  private long timeLeftInMillis;

  /** Countdown timer */
  private CountDownTimer timer;

  /** This Timer's BabyDraw. */
  private BabyDraw babyDraw;

  /**
   * Creates a new timer.
   */
  Timer(BabyDraw babyDraw) {
    timeLeftInMillis = 30000;
    this.babyDraw = babyDraw;
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
