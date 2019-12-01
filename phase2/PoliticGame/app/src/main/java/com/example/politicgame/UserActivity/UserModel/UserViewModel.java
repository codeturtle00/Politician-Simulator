package com.example.politicgame.UserActivity.UserModel;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.UserActivity.UserInterface.FormState;
import com.example.politicgame.UserActivity.UserInterface.ViewModelResult;

/** Model where contains common logic for user activity ,specifically
 * it checks if the username and password are valid .
 * Note it has child classes : LoginViewModel and RegisterViewModel*/
public abstract class UserViewModel extends ViewModel {
    private MutableLiveData<FormState> formState = new MutableLiveData<>();
    protected PoliticGameApp app;
    protected MutableLiveData<ViewModelResult> dataResult = new MutableLiveData<>();
    public LiveData<ViewModelResult> getViewModelResult() { return dataResult;}
    public LiveData<FormState> getFormState() { return formState;}
    protected UserViewModel(Context context){
        Activity activity = (Activity) context;
        app = (PoliticGameApp) activity.getApplication();

    }
    /** This validates username so that its not null or it is a valid email address*/
    protected boolean usernameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    /** This validates password so that its length is greater than 4*/
    protected boolean passwordValid(String password) {
        return password != null && password.trim().length() > 4;
    }

    /** Based on validation result,set different loginFormState message*/
    public void dataValidate(String username, String password) {
        if (!usernameValid(username)) {
            formState.setValue(new FormState(R.string.invalid_username, null));
        } else if (!passwordValid(password)) {
            formState.setValue(new FormState(null, R.string.invalid_password));
        } else {
            formState.setValue(new FormState(true));
        }
    }

    public abstract void databaseRequest(String username, String password);
}
