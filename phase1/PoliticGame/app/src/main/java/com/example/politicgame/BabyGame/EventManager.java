package com.example.politicgame.BabyGame;

import android.graphics.Canvas;

import java.util.ArrayList;

public class EventManager {
    ArrayList<Event> events;

    EventManager() {
        events = new ArrayList<>();
    }

    public void update() {
        for (Event event:events) {
            event.update();
        }
    }

    public void draw(Canvas canvas) {
        for (Event event:events) {
            event.draw(canvas);
        }
    }
}
