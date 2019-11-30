package com.example.politicgame.LoadCharacter;

import android.widget.Button;

public interface CellInfo {
    /**Returns the text for the cell's button
     *
     * @return Text for the cell's button, either Delete or New
     */
    public String getButtonText();

    /**
     * Alters the visibility of the cell's Delete Character and Create Character
     * buttons.
     *
     * @param createButton Button for character creation
     * @param deleteButton Button for character deletion
     */
    public void showCreateDeleteButtons(Button createButton, Button deleteButton);


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
