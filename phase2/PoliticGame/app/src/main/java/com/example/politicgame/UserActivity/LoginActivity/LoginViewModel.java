package com.example.politicgame.UserActivity.LoginActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.politicgame.UserActivity.FormState;
import com.example.politicgame.UserActivity.UserViewModel;
import com.example.politicgame.UserData.LoginRepository;
import com.example.politicgame.UserData.Result;
import com.example.politicgame.Character.UserAccount;
import com.example.politicgame.R;

public class LoginViewModel extends UserViewModel {

  private MutableLiveData<FormState> loginFormState = new MutableLiveData<>();
  private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
  private LoginRepository loginRepository;

  public void setLoginRepository(LoginRepository repository) {
    this.loginRepository = repository;
  }

  LoginViewModel(LoginRepository loginRepository) {
    this.loginRepository = loginRepository;
  }

  LiveData<FormState> getLoginFormState() {
    return loginFormState;
  }

  LiveData<LoginResult> getLoginResult() {
    return loginResult;
  }

  public void login(String username, String password) {
    // can be launched in a separate asynchronous job
    Result<UserAccount> result = loginRepository.login(username, password);

    if (result instanceof Result.Success) {
      UserAccount data = ((Result.Success<UserAccount>) result).getData();
      loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
    } else {
      loginResult.setValue(new LoginResult(R.string.login_failed));
    }
  }

  public void loginDataChanged(String username, String password) {
    if (!isUserNameValid(username)) {
      loginFormState.setValue(new FormState(R.string.invalid_username, null));
    } else if (!isPasswordValid(password)) {
      loginFormState.setValue(new FormState(null, R.string.invalid_password));
    } else {
      loginFormState.setValue(new FormState(true));
    }
  }
}
