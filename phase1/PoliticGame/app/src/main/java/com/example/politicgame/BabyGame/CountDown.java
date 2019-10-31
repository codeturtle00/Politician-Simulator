package com.example.politicgame.BabyGame;

public class CountDown implements Runnable {
    private BabyView babyView;
    private long timeLeft;
    private boolean isRunning;

    CountDown(BabyView babyView, long timeLeft) {
        this.babyView = babyView;
        this.timeLeft = timeLeft;
    }

    @Override
    public void run() {}
}
