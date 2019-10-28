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
public class LoginDataSource {
    private Context context;
    public LoginDataSource(Context context){
        this.context = context;
    }
    private static final String FILE_NAME = "userLogin.json";
    public boolean userAuthentication(String username, String password) {
        String jsonString = new String();
        try {

            FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            System.out.println( "File is empty?" +fileInputStream.toString());
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonList = new JSONArray(jsonString);
            for (int i = 0; i < jsonList.length(); i++) {
                if (jsonList.getJSONObject(i).getString("UserName").equals(username)) {
                    return jsonList.getJSONObject(i).getString("Password").equals(password);
                }
            }
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Result<LoggedInUser> login(String username, String password) {
                try{
                    if (this.userAuthentication(username,password)){
                    LoggedInUser user = new LoggedInUser( java.util.UUID.randomUUID().toString(),username);
                    return new Result.Success<>(user);}
                    else{
                        return new Result.Error(new IOException("Error logging in"));
                    }
                }
            catch (Exception e){
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
