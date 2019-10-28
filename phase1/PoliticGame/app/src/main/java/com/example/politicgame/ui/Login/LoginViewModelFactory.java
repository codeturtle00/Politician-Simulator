package com.example.politicgame.ui.Login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.politicgame.data.LoginDataSource;
import com.example.politicgame.data.LoginRepository;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private LoginActivity loginActivity;
    public LoginViewModelFactory(LoginActivity loginActivity){
        this.loginActivity = loginActivity;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource(this.loginActivity)));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
