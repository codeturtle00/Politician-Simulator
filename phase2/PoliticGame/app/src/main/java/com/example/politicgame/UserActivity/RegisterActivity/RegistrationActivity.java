package com.example.politicgame.UserActivity.RegisterActivity;

import androidx.annotation.Nullable;

import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.politicgame.Common.FileSavingService;
import com.example.politicgame.GameActivity;
import com.example.politicgame.MainActivity;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.UserActivity.FormState;

public class RegistrationActivity extends GameActivity {
  private FileSavingService fileSaving;
  private RegisterViewModel registerViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.registerViewModel = new RegisterViewModel(this);
    setContentView(R.layout.activity_registration);

    setTitle("Registration");
    app = (PoliticGameApp) getApplication();
    this.fileSaving = new FileSavingService(this);
    final EditText username = findViewById(R.id.username);
    final EditText password = findViewById(R.id.password);
    final Button saveButton = findViewById(R.id.save);
    final Button backButton = findViewById(R.id.sign_out);
    backButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            BackToMenu();
          }
        });

    registerViewModel
        .getRegisterFormState()
        .observe(
            this,
            new Observer<FormState>() {
              @Override
              public void onChanged(@Nullable FormState RegisterFormState) {
                if (RegisterFormState == null) {
                  return;
                }
                saveButton.setEnabled(RegisterFormState.isDataValid());
                if (RegisterFormState.getUsernameError() != null) {
                  username.setError(getString(RegisterFormState.getUsernameError()));
                }
                if (RegisterFormState.getPasswordError() != null) {
                  password.setError(getString(RegisterFormState.getPasswordError()));
                }
              }
            });

    TextWatcher afterTextChangedListener =
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // ignore
          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            // ignore
          }

          @Override
          public void afterTextChanged(Editable s) {
            registerViewModel.registerDataValidate(
                username.getText().toString(), password.getText().toString());
          }
        };
    username.addTextChangedListener(afterTextChangedListener);
    password.addTextChangedListener(afterTextChangedListener);
    saveButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            registerViewModel.saveUserToDb(
                username.getText().toString(), password.getText().toString());
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

  public void BackToMenu() {
    /** Returns to main menu */
    Intent switchBabyIntent = new Intent(this, MainActivity.class);
    startActivity(switchBabyIntent);
    finish();
  }
}
