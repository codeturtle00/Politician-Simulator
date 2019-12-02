package com.example.politicgame.UserActivity.UserInterface;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.lifecycle.Observer;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.PopUpActivity;
import com.example.politicgame.UserActivity.UserModel.ViewModelResult;
import com.example.politicgame.UserActivity.UserModel.UserViewModel;

abstract class UserPopUpActivity extends PopUpActivity {

  /** The application. */
  protected PoliticGameApp app;

  protected void setFormStateListener(
      UserViewModel viewModel,
      final Button buttons,
      final EditText usernameEditText,
      final EditText passwordEditText) {
    viewModel
        .getFormState()
        .observe(
            this,
            new Observer<FormState>() {
              @Override
              public void onChanged(@Nullable FormState dataFormState) {
                if (dataFormState == null) {
                  return;
                }
                buttons.setEnabled(dataFormState.isDataValid());
                if (dataFormState.getUsernameError() != null) {
                  usernameEditText.setError(getString(dataFormState.getUsernameError()));
                }
                if (dataFormState.getPasswordError() != null) {
                  passwordEditText.setError(getString(dataFormState.getPasswordError()));
                }
              }
            });
  }

  protected void setModelResultListener(
      UserViewModel viewModel, final ProgressBar loadingProgressBar) {
    viewModel
        .getViewModelResult()
        .observe(
            this,
            new Observer<ViewModelResult>() {
              @Override
              public void onChanged(@Nullable ViewModelResult modelResult) {
                if (modelResult == null) {
                  return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (modelResult.getError() != null) {
                  databaseAccessDeny(modelResult.getError());
                }
                if (modelResult.getInvalid() != null) {
                  invalidUserMessage(modelResult.getInvalid());
                }
                if (modelResult.getSuccess() != null) {
                  updateUiWithUser(modelResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);
              }
            });
  }

  protected void setKeyBoardListener(
      final UserViewModel viewModel,
      final EditText usernameEditText,
      final EditText passwordEditText) {
    passwordEditText.setOnEditorActionListener(
        new TextView.OnEditorActionListener() {
          @Override
          public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
              viewModel.databaseRequest(
                  usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
            return false;
          }
        });
  }

  protected void textWatcher(
      final UserViewModel viewModel,
      final EditText usernameEditText,
      final EditText passwordEditText) {

    TextWatcher afterTextChangedListener =
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {}

          @Override
          public void afterTextChanged(Editable s) {
            viewModel.dataValidate(
                usernameEditText.getText().toString(), passwordEditText.getText().toString());
          }
        };
    usernameEditText.addTextChangedListener(afterTextChangedListener);
    passwordEditText.addTextChangedListener(afterTextChangedListener);
  }

  protected abstract void databaseAccessDeny(@StringRes Integer errorString);

  protected abstract void invalidUserMessage(String invalidMessage);

  protected abstract void updateUiWithUser(LoggedInUserView model);
}
