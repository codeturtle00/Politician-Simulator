package com.example.politicgame.UserActivity.LoginActivity;

import android.app.Activity;

import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.MainActivity;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.UserActivity.FormState;
import com.example.politicgame.UserActivity.RegisterActivity.RegistrationActivity;

import org.json.JSONArray;
import org.json.JSONException;

/** An activity responsible for login*/
public class LoginActivity extends AppCompatActivity {
  private PoliticGameApp app;
  private LoginViewModel loginViewModel;
  private FileSavingService fileSaving;

  private void register() {
    Intent registerIntent = new Intent(this, RegistrationActivity.class);
    startActivity(registerIntent);
    finish();
  }

  @Override
  protected void onStart() {
    super.onStart();
    final Button registration = findViewById(R.id.signup);
    registration.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            register();
          }
        });
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    /** Set up theme,layout,title for loginActivity */
    app = (PoliticGameApp) getApplication();
    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    setTitle("Login");
    /** Initialize fileSaving and ViewModel */
    this.fileSaving = new FileSavingService(this);
    this.loginViewModel =
        ViewModelProviders.of(this, new LoginViewModelFactory(this)).get(LoginViewModel.class);
    /** Give labels to layout */
    final EditText usernameEditText = findViewById(R.id.username);
    final EditText passwordEditText = findViewById(R.id.password);
    final Button loginButton = findViewById(R.id.login);
    final ProgressBar loadingProgressBar = findViewById(R.id.loading);
    final Button backButton = findViewById(R.id.sign_out);
    /**
     * Observe the form state of the username and password, set error state if there is a formState
     * Error
     */
    loginViewModel
        .getLoginFormState()
        .observe(
            this,
            new Observer<FormState>() {
              @Override
              public void onChanged(@Nullable FormState loginFormState) {
                if (loginFormState == null) {
                  return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                  usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                  passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
              }
            });
    /**
     * Observe the result state of the username and password, if there is an error login in ,go to
     * showLoginFailed; if login in successfully,go to updateUiWithUser
     */
    loginViewModel
        .getLoginResult()
        .observe(
            this,
            new Observer<LoginResult>() {
              @Override
              public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                  return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                      showLoginFailed(loginResult.getError());
                  }
                  if (loginResult.getNull() != null) {
                      showNoUserFound(loginResult.getNull());
                  }
                if (loginResult.getSuccess() != null) {
                  updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);
              }
            });
    /** User input of login,passing input and update formState */
    TextWatcher afterTextChangedListener =
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {}

          @Override
          public void afterTextChanged(Editable s) {
            loginViewModel.loginDataChanged(
                usernameEditText.getText().toString(), passwordEditText.getText().toString());
          }
        };
    usernameEditText.addTextChangedListener(afterTextChangedListener);
    passwordEditText.addTextChangedListener(afterTextChangedListener);

    passwordEditText.setOnEditorActionListener(
        new TextView.OnEditorActionListener() {
          @Override
          public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
              loginViewModel.login(
                  usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
            return false;
          }
        });

    loginButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            loadingProgressBar.setVisibility(View.VISIBLE);
            loginViewModel.login(
                usernameEditText.getText().toString(), passwordEditText.getText().toString());
          }
        });

    backButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            BackToMenu();
          }
        });
  }
  /**
   * If successfully login in, a.creates a new UserAccount object b.set a list of characters for the
   * object loading from the database c.sets it as the current logged in user putting inside
   * Singleton PoliticGame
   */
  private void updateUiWithUser(LoggedInUserView model) {
      String FILE_NAME = "user.json";
    String name = model.getDisplayName();
    UserAccount loginUser = new UserAccount(name, this);
    JSONArray jsonFile = fileSaving.readJsonFile(FILE_NAME);
    try {
      for (int i = 0; i < jsonFile.length(); i++) {
        String userFileName = jsonFile.getJSONObject(i).keys().next();
        if (userFileName.equals(name)) {
          loginUser.setCharArray(jsonFile.getJSONObject(i).getJSONArray(name));
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    app.setCurrentUser(loginUser);
    String welcome = getString(R.string.welcome) + name;
    Intent startIntent = new Intent(this, MainActivity.class);
    startActivity(startIntent);
    Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    finish();
  }
  /** If have error login in,stay in Login In Page and clear username and password */
  private void showLoginFailed(@StringRes Integer errorString) {
    Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    final EditText passwordEditText = findViewById(R.id.password);
    passwordEditText.setText("");
    final EditText usernameEditText = findViewById(R.id.username);
    usernameEditText.setText("");
  }
    /** If no user found in the database,go to Register Page */
    private void showNoUserFound(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
        Intent registerIntent = new Intent(this, RegistrationActivity.class);
        startActivity(registerIntent);
        finish();
    }
  /** Returns to main menu */
  public void BackToMenu() {
    Intent switchBabyIntent = new Intent(this, MainActivity.class);
    startActivity(switchBabyIntent);
    finish();
  }
}
