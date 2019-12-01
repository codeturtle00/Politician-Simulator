package com.example.politicgame;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.UserActivity.FormState;
import com.example.politicgame.UserActivity.LoginActivity.LoggedInUserView;
import com.example.politicgame.UserActivity.LoginActivity.ViewModelResult;
import com.example.politicgame.UserActivity.UserViewModel;

public abstract class PopUpActivity extends FragmentActivity {

  /** The application. */
  protected PoliticGameApp app;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);

    int width = dm.widthPixels;
    int height = dm.heightPixels;

    getWindow().setLayout((int) (width * .8), (int) (height * .8));

    app = (PoliticGameApp) getApplication();
    setTheme(R.style.PopUp);
  }
}
