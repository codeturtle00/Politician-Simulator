package com.example.politicgame.UserActivity.UserInterface;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.lifecycle.ViewModelProviders;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.UserActivity.UserModel.LoggedInUserView;
import com.example.politicgame.UserActivity.UserModel.LoginViewModel;
import com.example.politicgame.UserActivity.UserDatabase.LoginViewModelFactory;

/** An activity responsible for login */
public class LoginActivity extends UserPopUpActivity {
  private LoginViewModel loginViewModel;

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    // Set up theme,layout,title for loginActivity
    super.onCreate(savedInstanceState);

    app = (PoliticGameApp) getApplication();
    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }

    setContentView(R.layout.activity_login);
    setTitle(R.string.login);

    this.loginViewModel =
        ViewModelProviders.of(this, new LoginViewModelFactory(this)).get(LoginViewModel.class);

    // Give labels to layout
    final EditText usernameEditText = findViewById(R.id.username);
    final EditText passwordEditText = findViewById(R.id.password);
    final Button loginButton = findViewById(R.id.login);
    final ProgressBar loadingProgressBar = findViewById(R.id.loading);
    final ImageButton closeButton = findViewById(R.id.closeLogin);
    final Button registration = findViewById(R.id.signup);
    Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
    usernameEditText.startAnimation(slide_up);
    passwordEditText.startAnimation(slide_up);
    loginButton.startAnimation(slide_up);
    registration.startAnimation(slide_up);

    // Observe the form state of the username and password, set error state if there is a formState
    // Error
    setFormStateListener(loginViewModel, loginButton, usernameEditText, passwordEditText);
    // Observe the login result and go to different use cases based on the result
    setModelResultListener(loginViewModel, loadingProgressBar);
    // SetupTextWatcher
    textWatcher(loginViewModel, usernameEditText, passwordEditText);
    // Set up keyboard listener
    setKeyBoardListener(loginViewModel, usernameEditText, passwordEditText);
    // Set up button listener
    loginButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            loadingProgressBar.setVisibility(View.VISIBLE);
            loginViewModel.databaseRequest(
                usernameEditText.getText().toString(), passwordEditText.getText().toString());
          }
        });

    closeButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            finish();
          }
        });

    registration.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            goToRegister();
          }
        });
  }
  /** Display user name if login in successfully */
  @Override
  protected void updateUiWithUser(LoggedInUserView model) {
    String welcome = getString(R.string.welcome) + model.getDisplayName();
    Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    finish();
  }
  /** If have error login in,stay in Login In Page and clear username and password */
  @Override
  protected void databaseAccessDeny(@StringRes Integer errorString) {
    Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    final EditText passwordEditText = findViewById(R.id.password);
    passwordEditText.setText("");
    final EditText usernameEditText = findViewById(R.id.username);
    usernameEditText.setText("");
  }
  /** If no user found in the database,go to Register Page */
  @Override
  protected void invalidUserMessage(String invalidMessage){
    Toast.makeText(getApplicationContext(), invalidMessage, Toast.LENGTH_SHORT).show();
    Intent registerIntent = new Intent(this, RegistrationActivity.class);
    startActivity(registerIntent);
    finish();
  }
  /** Go to registration Activity after validation fails */
  private void goToRegister() {
    Intent registerIntent = new Intent(this, RegistrationActivity.class);
    startActivity(registerIntent);
    finish();
  }
}
