package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;

class EventFactory {
  /**
   * Creates and returns an event based on string.
   *
   * @param event the event to be created
   * @param baby the baby used in this event
   * @param res the resources used to draw the event
   * @return new event
   */
  Event createEvent(String event, Baby baby, Resources res) {
    if (event.equalsIgnoreCase("KISS")) {
      return new Kiss(baby, res);
    } else if (event.equalsIgnoreCase("TICKLE")) {
      return new Tickle(baby, res);
    } else if (event.equalsIgnoreCase("HORIZONTALSHAKE")) {
      return new HorizontalShake(baby, res);
    } else if (event.equalsIgnoreCase("VERTICALSHAKE")) {
      return new VerticalShake(baby, res);
    }
    return null;
  }
}
