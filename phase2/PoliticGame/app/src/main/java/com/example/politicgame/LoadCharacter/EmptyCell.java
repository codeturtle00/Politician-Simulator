package com.example.politicgame.LoadCharacter;

import android.view.View;

public class EmptyCell implements CellInfo {
    private final String BUTTONTEXT = "New";
    private final String CELLTEXT = "Press button to create character";

    public EmptyCell(){}

    /**
     * Removes the cell's Delete Character button. Shows the cell's Create Character
     * button, since character doesn't exists.
     *
     * @param createButton Button for character creation
     * @param deleteButton Button for character deletion
     */
    public void setCreateDeleteButtons(View createButton, View deleteButton){
        createButton.setVisibility(View.VISIBLE);
        deleteButton.setVisibility(View.GONE);
    }

    /**Returns the text for the inside of the cell
     *
     * @return Text for the cell, includes character's name, completed elections and elections won
     */
    public String getCellText(){return CELLTEXT;}


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
