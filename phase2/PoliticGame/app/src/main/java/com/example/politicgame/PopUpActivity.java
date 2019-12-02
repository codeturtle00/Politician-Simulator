package com.example.politicgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import androidx.fragment.app.FragmentActivity;
import com.example.politicgame.Application.PoliticGameApp;

public abstract class PopUpActivity extends FragmentActivity {

  /** The application. */
  protected PoliticGameApp app;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTheme(R.style.PopUp);
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    int width = dm.widthPixels;
    int height = dm.heightPixels;
    getWindow().setLayout((int) (width * .8), (int) (height * .8));
    app = (PoliticGameApp) getApplication();
      if (app.isThemeBlue()) {
          setTheme(R.style.BlueTheme);
      } else {
          setTheme(R.style.RedTheme);
      }
  }

   protected void returnRequest(int requestCode) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", requestCode);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
