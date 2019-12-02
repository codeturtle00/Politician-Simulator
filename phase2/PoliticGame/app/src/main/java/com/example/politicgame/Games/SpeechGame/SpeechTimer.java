package com.example.politicgame.Games.SpeechGame;

class SpeechTimer {
    private int time;
    int getTimes(){
        return time;
    }
    SpeechTimer(){
        this.time = 0;
    }

    void addTimes(){
        this.time = this.time + 1;
    }
}
