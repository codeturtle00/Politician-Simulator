package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;

import com.example.politicgame.BabyGame.BabyActivity;
import com.example.politicgame.User.PoliticianA;
import com.example.politicgame.User.PoliticianB;
import com.example.politicgame.User.UserAccount;
import com.example.politicgame.User.UserAccountManager;

import org.json.JSONObject;

public class SelectCharacterActivity extends AppCompatActivity {

    private PoliticGameApp app;
    private UserAccountManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);

        setTitle("Select Character");
    }
    public void setCharacterA(View view){
        PoliticianA pA = new PoliticianA();
        this.userManager.loginUser.setCharArray(pA.getJsonCharacter());
        System.out.println(pA.getJsonCharacter());
        this.userManager.loginUser.saveToDb();
        Intent switchBabyIntent = new Intent(this, BabyActivity.class);
        startActivity(switchBabyIntent);
        finish();

    }
    public void setCharacterB(View view){
        PoliticianB pB = new PoliticianB();
        this.userManager.loginUser.setCharArray(pB.getJsonCharacter());
        this.userManager.loginUser.saveToDb();
        Intent switchBabyIntent = new Intent(this, BabyActivity.class);
        startActivity(switchBabyIntent);
        finish();

    }
}
