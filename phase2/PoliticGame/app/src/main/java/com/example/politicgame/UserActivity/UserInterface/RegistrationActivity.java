package com.example.politicgame.UserActivity.UserInterface;

import androidx.annotation.StringRes;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.UserActivity.UserModel.LoggedInUserView;
import com.example.politicgame.UserActivity.UserModel.RegisterViewModel;
import com.example.politicgame.UserActivity.UserDatabase.RegistrationDatabaseHandler;

public class RegistrationActivity extends UserPopUpActivity {
  private RegisterViewModel registerViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.registerViewModel = new RegisterViewModel(this, new RegistrationDatabaseHandler(this));
    setContentView(R.layout.activity_registration);
    setTitle("Registration");
    app = (PoliticGameApp) getApplication();
    // Declare features in this page
    final EditText usernameEditText = findViewById(R.id.username);
    final EditText passwordEditText = findViewById(R.id.password);
    final Button registerButton = findViewById(R.id.save);
    final ProgressBar loadingProgressBar = findViewById(R.id.loading);
    final Button backButton = findViewById(R.id.sign_out);
    final ImageButton closeButton = findViewById(R.id.closeRegister);
    // Observe the form state of the username and password, set error state if there is a formState
    // Error
    setFormStateListener(registerViewModel, registerButton, usernameEditText, passwordEditText);
    // SetupTextWatcher
    textWatcher(registerViewModel, usernameEditText, passwordEditText);
    // Set up keyboard listener
    setKeyBoardListener(registerViewModel, usernameEditText, passwordEditText);
    // Observe the login result and go to different use cases based on the result
    setModelResultListener(registerViewModel, loadingProgressBar);
    registerButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            registerViewModel.databaseRequest(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            System.out.println("register");
          }
        });

    closeButton.setOnClickListener(
            new View.OnClickListener() {
              public void onClick(View v) {
                finish();
              }
            });

    backButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            BackToLogin();
          }
        });
  }
  /** Returns to main menu */
  public void BackToLogin() {
    Intent switchLogin = new Intent(this, LoginActivity.class);
    startActivity(switchLogin);
    finish();
  }
  /** Display user name if login in successfully */
  @Override
  protected void updateUiWithUser(LoggedInUserView model) {
    String registerInfo = getString(R.string.registerInfo) + model.getDisplayName();
    Toast.makeText(getApplicationContext(), registerInfo, Toast.LENGTH_LONG).show();
    finish();
  }
  /** If have error login in,stay in Login In Page and clear username and password */
  @Override
  protected void databaseAccessDeny(@StringRes Integer errorString) {
    Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
  }
  /** If no user found in the database,go to Register Page */
  @Override
  protected void invalidUserMessage(String invalidMessage) {
    Toast.makeText(getApplicationContext(), invalidMessage, Toast.LENGTH_SHORT).show();
  }
}
