package com.example.politicgame.LoadCharacter;

import android.view.View;
import android.widget.Button;

public class EmptyCell implements CellInfo {
    private final String BUTTONTEXT = "New";
    private final String CELLTEXT = "Press button to create character";

    public EmptyCell(){}

    /**Returns the text for the cell's button
     *
     * @return Text for the cell's button. Returns "New".
     */
    public String getButtonText(){return BUTTONTEXT;}


    /**
     * Removes the cell's Delete Character button. Shows the cell's Create Character
     * button, since character doesn't exists.
     *
     * @param createButton Button for character creation
     * @param deleteButton Button for character deletion
     */
    public void showCreateDeleteButtons(Button createButton, Button deleteButton){
        createButton.setVisibility(View.VISIBLE);
        deleteButton.setVisibility(View.GONE);
    }


    /**Returns if the cell is loaded or not
     *
     * @return false, the cell is not loaded
     */
    public boolean isLoaded(){return false;}


    /**Returns the character's name
     *
     * @return null, the character has no name
     */
    public String getCharName(){return null;}
}
