package com.example.politicgame.data;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.politicgame.data.model.LoggedInUser;
import com.example.politicgame.ui.Login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource extends AppCompatActivity {

    public Result<LoggedInUser> login(String username, String password) {


                try{
                    LoggedInUser user = new LoggedInUser( java.util.UUID.randomUUID().toString(),username);
                return new Result.Success<>(user);}
            catch (Exception e){
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
