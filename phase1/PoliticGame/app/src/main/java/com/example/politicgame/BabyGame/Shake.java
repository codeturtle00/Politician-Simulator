//package com.example.politicgame.BabyGame;
//
//import android.os.Bundle;
//import android.view.MotionEvent;
//import android.widget.TextView;
//import androidx.core.view.GestureDetectorCompat;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.politicgame.R;
//
//public class Shake extends AppCompatActivity {
//  private GestureDetectorCompat shake = null;
//  private TextView score_display = null;
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_baby);
//
//    score_display = findViewById(R.id.score_display);
//
//    DetectSwipe gestureListener = new DetectSwipe();
//    gestureListener.setActivity(this);
//    shake = new GestureDetectorCompat(this, gestureListener);
//  }
//
//  @Override
//  public boolean onTouchEvent(MotionEvent event) {
//    shake.onTouchEvent(event);
//    return true;
//  }
//
//  void updateHappiness() {
//    BabyActivity.happiness += 1;
//    String score = BabyActivity.happiness.toString() + "%";
//    score_display.setText(score);
//  }
//}
