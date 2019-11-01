package com.example.politicgame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class LoadCharacterActivity extends GameActivity {
    protected PoliticGameApp app;
    private final String FILE_NAME = "user_game_data.json";
    private Drawable highlight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        //Set theme
        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        setTitle("Load Your Character");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_character);

        highlight = getResources().getDrawable(R.drawable.highlight);

        final TextView charButton1 = findViewById(R.id.character_1);
        final TextView charButton2 = findViewById(R.id.character_2);

        charButton1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        charButton1.setBackground(highlight);
                        charButton2.setBackground(null);
                    }
                });

        charButton2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        charButton2.setBackground(highlight);
                        charButton1.setBackground(null);
                    }
                });
    }

}
