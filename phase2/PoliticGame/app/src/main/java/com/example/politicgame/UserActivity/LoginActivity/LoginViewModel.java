package com.example.politicgame.UserActivity.LoginActivity;

import android.content.Context;

import com.example.politicgame.UserActivity.UserViewModel;
import com.example.politicgame.UserData.LoginRepository;
import com.example.politicgame.UserData.Result;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.R;

public class LoginViewModel extends UserViewModel {
  private LoginRepository loginRepository;
  LoginViewModel(Context context, LoginRepository loginRepository) {
    super(context);
    this.loginRepository = loginRepository; }
    @Override
  public void databaseRequest(String username, String password) {
    Result result = loginRepository.login(username, password);
     if (result instanceof Result.Success) {
      UserAccount account = ((Result.Success) result).getData();
       this.app.setCurrentUser(account);
      dataResult.setValue(new ViewModelResult(new LoggedInUserView(account.getDisplayName())));
    } else if (result instanceof Result.InvalidResult.EmptyResult) {
      dataResult.setValue(new ViewModelResult(this.app.getString(R.string.user_not_found)+ ((Result.InvalidResult.EmptyResult) result).getLostData()));}
    else{
      dataResult.setValue(new ViewModelResult(R.string.login_failed));
    }
  }
}
