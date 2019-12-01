package com.example.politicgame.UserActivity.UserModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.politicgame.UserActivity.UserDatabase.LoginDatabaseHandler;
import com.example.politicgame.UserActivity.UserInterface.LoginActivity;

/**
 * ViewModel provider factory to instantiate LoginViewModel. Required given LoginViewModel has a
 * non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {
  private LoginActivity loginActivity;

  public LoginViewModelFactory(LoginActivity loginActivity) {
    this.loginActivity = loginActivity;
  }

  @NonNull
  @Override
  @SuppressWarnings("unchecked")
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(LoginViewModel.class)) {
      return (T)
          new LoginViewModel(this.loginActivity, LoginRepository.getInstance(new LoginDatabaseHandler(this.loginActivity)));
    } else {
      throw new IllegalArgumentException("Unknown ViewModel class");
    }
  }
}
