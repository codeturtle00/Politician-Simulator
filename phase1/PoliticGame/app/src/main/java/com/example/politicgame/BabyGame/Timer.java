package com.example.politicgame.BabyGame;

import android.os.CountDownTimer;
import android.widget.TextView;

class Timer {

    private long timeLeftInMillis;
    private TextView timerView;
    private CountDownTimer timer;

    Timer(final TextView timerView) {
        this.timerView = timerView;
        timeLeftInMillis = 60000;
    }

    void pause() {
        timer.cancel();
        timer = null;
    }

    void resume() {
    System.out.println("resuming with time " + timeLeftInMillis);
    timer =
        new CountDownTimer(timeLeftInMillis, 1000) {

          public void onTick(long millisUntilFinished) {
            timeLeftInMillis = millisUntilFinished;
            System.out.println((int) millisUntilFinished / 1000);
            timerView.setText(
                String.format(Integer.toString((int) millisUntilFinished / 1000)));
          }

          public void onFinish() {
            timerView.setText("Time's Up!");
          }
        };
        timer.start();
    }
}
