package com.example.politicgame.LoadCharacter;

import android.view.View;
import android.widget.Button;

public class LoadedCell implements CellInfo {
    private String BUTTONTEXT = "Delete";
    private String cellText;
    private String charName;

    public LoadedCell(String msg, String charName){
        this.cellText = msg;
        this.charName = charName;
    }

    /**Returns the text for the cell's button
     *
     * @return Text for the cell's button. Returns "Delete"
     */
    public String getButtonText(){return BUTTONTEXT;}


    /**
     * Show the cell's Delete Character button. Removes the cell's Create Character
     * button, since character already exists.
     *
     * @param createButton Button for character creation
     * @param deleteButton Button for character deletion
     */
    public void showCreateDeleteButtons(Button createButton, Button deleteButton){
    createButton.setVisibility(View.GONE);
    deleteButton.setVisibility(View.VISIBLE);
  }


    /**Returns if the cell is loaded or not
     *
     * @return true, the cell is loaded
     */
    public boolean isLoaded(){return true;}


    /**Returns the character's name
     *
     * @return The character's name
     */
    public String getCharName(){return charName;}
}
