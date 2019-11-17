package com.example.politicgame.UserActivity;

import android.util.Patterns;

import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    /** This validates username so that its not null or it is a valid email address*/
    protected boolean isUserNameValid(String username) {
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
    protected boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 4;
    }
}
