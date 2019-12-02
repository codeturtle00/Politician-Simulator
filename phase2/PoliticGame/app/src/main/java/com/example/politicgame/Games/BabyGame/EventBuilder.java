package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;

class EventBuilder {
  /**
   * A builder which constructs events and sets the x, y, width, and height attributes. This class
   * also uses EventFactory to construct the correct event.
   */
  private EventFactory eventFactory = new EventFactory();

  private Event event;
  private int babyX;
  private int babyY;
  private int babyWidth;
  private int babyHeight;
  private Resources res;

  EventBuilder(String event) {
    this.event = eventFactory.createEvent(event);
  }

  EventBuilder addBabyX(int babyX) {
    this.babyX = babyX;
    return this;
  }

  EventBuilder addBabyY(int babyY) {
    this.babyY = babyY;
    return this;
  }

  EventBuilder addBabyWidth(int babyWidth) {
    this.babyWidth = babyWidth;
    return this;
  }

  EventBuilder addBabyHeight(int babyHeight) {
    this.babyHeight = babyHeight;
    return this;
  }

  EventBuilder addBabyRes(Resources res) {
    this.res = res;
    return this;
  }

  /**
   * Builds and returns the event. Also sets the event vitals so that the event can be properly
   * used.
   *
   * @return the event
   */
  Event build() {
    event.setBabyX(babyX);
    event.setBabyY(babyY);
    event.setBabyWidth(babyWidth);
    event.setBabyHeight(babyHeight);
    event.setRes(res);
    event.setEventVitals();
    return event;
  }

  class EventFactory {
    /**
     * Creates and returns an event based on string.
     *
     * @param event the event to be created
     * @return new event
     */
    Event createEvent(String event) {
      if (event.equalsIgnoreCase("KISS")) {
        return new Kiss();
      } else if (event.equalsIgnoreCase("TICKLE")) {
        return new Tickle();
      } else if (event.equalsIgnoreCase("HORIZONTALSHAKE")) {
        return new HorizontalShake();
      } else if (event.equalsIgnoreCase("VERTICALSHAKE")) {
        return new VerticalShake();
      }
      return null;
    }
  }
}
