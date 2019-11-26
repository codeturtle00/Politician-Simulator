package com.example.politicgame.Character;

import android.widget.ImageView;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.R;

public class SpriteSetter {
    PoliticGameApp app;
    UserAccount currentUser;

    public SpriteSetter(PoliticGameApp app){
        this.app = app;
        this.currentUser = app.getCurrentUser();
    }

    public void setSprite(ImageView image){
        int charId = currentUser.getCharId(app.getCurrentCharacter());

        if (charId == 1) {
            image.setImageResource(R.drawable.jake);
//      pauseImage.setBackgroundResource(R.drawable.jake);
        } else if (charId == 2) {
            image.setImageResource(R.drawable.helmet_guy);
//      pauseImage.setBackgroundResource(R.drawable.helmet_guy);
        } else {
            image.setImageResource(R.drawable.pause_filler);
//      pauseImage.setBackgroundResource(R.drawable.pause_filler);
        }
    }
}
