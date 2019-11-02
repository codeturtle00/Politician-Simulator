package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.politicgame.UserActivity.LoginActivity.LoggedInActivity;
import com.example.politicgame.Character.UserAccount;

public class PauseActivity extends GameActivity{

    private final int DEFAULT_CODE = 0;
    private final int RESUME_CODE = 1;
    private final int QUIT_CODE = 2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        setTitle("Paused");

        UserAccount currUser = app.getCurrentUser();
        int charId = currUser.getCharId(app.getCurrentCharacter());

        final ImageView pauseImage = findViewById(R.id.pause_menu_image);

        if (charId == 1){
            pauseImage.setImageResource(R.drawable.trump);
            pauseImage.setBackgroundResource(R.drawable.trump);
        } else if (charId == 2) {
            pauseImage.setImageResource(R.drawable.helmet_guy);
            pauseImage.setBackgroundResource(R.drawable.helmet_guy);
        } else {
            pauseImage.setImageResource(R.drawable.pause_filler);
            pauseImage.setBackgroundResource(R.drawable.pause_filler);
        }


        //Resume button
        final Button resumeB = findViewById(R.id.resume);
        resumeB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        returnRequest(RESUME_CODE);
                    }
                });

        //Quit to main menu button
        final Button quitB = findViewById(R.id.sign_out);
        quitB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        returnRequest(QUIT_CODE);
                    }
                });

        //Quit to logged in menu button
        final Button loggedInB = findViewById(R.id.pause_menu_load_screen);
        loggedInB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        openLoggedInActivity();
                    }
                });

    }

    private void openLoggedInActivity(){
        Intent LoggedInActivityIntent = new Intent(this, LoggedInActivity.class);
        startActivity(LoggedInActivityIntent);
    }


    private void returnRequest(int requestCode){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", requestCode);

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
