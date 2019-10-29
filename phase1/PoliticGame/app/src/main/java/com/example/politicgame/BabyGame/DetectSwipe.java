//package com.example.politicgame.BabyGame;
//
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//
///** Code based on "https://www.dev2qa.com/how-to-detect-swipe-gesture-direction-in-android/" */
//public class DetectSwipe extends GestureDetector.SimpleOnGestureListener {
//
//  // Minimal x and y axis swipe distance.
//  private static int MIN_SWIPE_DISTANCE_X = 500;
//  private static int MIN_SWIPE_DISTANCE_Y = 500;
//
//  // Maximal x and y axis swipe distance.
//  private static int MAX_SWIPE_DISTANCE_X = 1000;
//  private static int MAX_SWIPE_DISTANCE_Y = 1000;
//
//  // Source activity that display message in text view.
//  private Shake activity;
//
//  void setActivity(Shake activity) {
//    this.activity = activity;
//  }
//
//  /* This method is invoked when a swipe gesture happened. */
//  @Override
//  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//    float deltaX = e1.getX() - e2.getX();
//    float deltaY = e1.getY() - e2.getY();
//
//    // Only when swipe distance between minimal and maximal distance value then we treat it as
//    // effective swipe
//    if ((Math.abs(deltaX) >= MIN_SWIPE_DISTANCE_X) && (Math.abs(deltaX) <= MAX_SWIPE_DISTANCE_X)) {
//      this.activity.updateHappiness();
//    }
//
//    if ((Math.abs(deltaY) >= MIN_SWIPE_DISTANCE_Y) && (Math.abs(deltaY) <= MAX_SWIPE_DISTANCE_Y)) {
//      this.activity.updateHappiness();
//    }
//    return true;
//  }
//}
