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


    /**
     * Sets the ImageView object's image source to be the currently selected character's sprite
     *
     * @param image The ImageView object to set an image to
     */
    public void setSprite(ImageView image){
        int charId = currentUser.getCharId(app.getCurrentCharacter());

        if (charId == 1) {
            image.setImageResource(R.drawable.jake);
            //pauseImage.setBackgroundResource(R.drawable.jake);
        } else if (charId == 2) {
            image.setImageResource(R.drawable.helmet_guy);
            //pauseImage.setBackgroundResource(R.drawable.helmet_guy);
        } else if (charId == 3) {
            image.setImageResource(R.drawable.saitama);
            //pauseImage.setBackgroundResource(R.drawable.helmet_guy);
        } else if (charId == 4) {
            image.setImageResource(R.drawable.tohru);
            //pauseImage.setBackgroundResource(R.drawable.helmet_guy);
        } else if (charId == 5) {
            image.setImageResource(R.drawable.paul_gries);
            //pauseImage.setBackgroundResource(R.drawable.helmet_guy);
        }
        else {
            image.setImageResource(R.drawable.pause_filler);
//      pauseImage.setBackgroundResource(R.drawable.pause_filler);
        }
    }
}
