package com.example.politicgame.Games.BabyGame;

import android.os.CountDownTimer;

class BabyGameTimer {
  /** Time arrow_left. */
  private long timeLeftInMillis;

  /** Countdown timer */
  private CountDownTimer timer;

  /** This BabyGameTimer's DefaultBGActivity. */
  private DefaultBGActivity defaultBGActivity;

  /** Creates a new timer. */
  BabyGameTimer(DefaultBGActivity defaultBGActivity, int initTime) {
    timeLeftInMillis = initTime;
    this.defaultBGActivity = defaultBGActivity;
  }

  /** Cancels current timer object */
  void pause() {
    if (timer != null) {
      timer.cancel();
      timer = null;
    }
  }

  /** Resumes timer with however much time the player had arrow_left before. */
  void resume() {
    System.out.println("resuming with time " + timeLeftInMillis);
    timer =
        new CountDownTimer(timeLeftInMillis, 1000) {

          public void onTick(long millisUntilFinished) {
            timeLeftInMillis = millisUntilFinished;
            Integer timeLeft = (int) timeLeftInMillis / 1000;
            System.out.println(timeLeft);
            defaultBGActivity.updateTime(timeLeft.toString(), false);
          }

          public void onFinish() {
            defaultBGActivity.updateTime("Time's up!", true);
          }
        };
    timer.start();
  }
}
