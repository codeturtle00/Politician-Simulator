package com.example.politicgame.CharacterSelect;

import android.view.View;

public interface CellInfo {

    /**
     * Alters the visibility of the cell's Delete Character and Create Character
     * buttons.
     *
     * @param createButton Button for character creation
     * @param deleteButton Button for character deletion
     */
    public void setCreateDeleteButtons(View createButton, View deleteButton);

    /**Returns the text for the inside of the cell
     *
     * @return Text for the cell, dependent on if the cell is loaded or empty
     */
    public String getCellText();


    /**Returns if the cell is loaded or not
     *
     * @return Is the cell loaded?
     */
    public boolean isLoaded();


    /**Returns the character's name
     *
     * @return The character's name
     */
    public String getCharName();
}
