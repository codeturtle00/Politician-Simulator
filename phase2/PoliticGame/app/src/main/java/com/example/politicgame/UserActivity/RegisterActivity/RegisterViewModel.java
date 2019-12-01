package com.example.politicgame.UserActivity.RegisterActivity;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.R;
import com.example.politicgame.UserActivity.FormState;
import com.example.politicgame.UserActivity.LoginActivity.LoggedInUserView;
import com.example.politicgame.UserActivity.LoginActivity.ViewModelResult;
import com.example.politicgame.UserActivity.UserViewModel;
import com.example.politicgame.UserData.Result;


public class RegisterViewModel extends UserViewModel {
  private RegistrationDatabaseHandler registerSaving;
  RegisterViewModel(Context context, RegistrationDatabaseHandler registerSaving){
    super(context);
    this.registerSaving = registerSaving;
  }
@Override
  public void databaseRequest(String userName,String password){
    Result result = registerSaving.register(userName,password);
    if (result instanceof Result.Success) {
      UserAccount account = ((Result.Success) result).getData();
      this.app.setCurrentUser(account);
      dataResult.setValue(new ViewModelResult(new LoggedInUserView(account.getDisplayName())));
    } else if (result instanceof Result.InvalidResult.DuplicateResult) {
      dataResult.setValue(new ViewModelResult(this.app.getString(R.string.duplicate_user) + ((Result.InvalidResult.DuplicateResult) result).getLostData()));}
    else{
      dataResult.setValue(new ViewModelResult(R.string.register_failed));
    }
  }
}
