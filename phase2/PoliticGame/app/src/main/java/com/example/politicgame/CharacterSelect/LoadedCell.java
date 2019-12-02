package com.example.politicgame.CharacterSelect;

import android.view.View;

class LoadedCell implements CellInfo {
    private String BUTTONTEXT = "Delete";
    private String cellText;
    private String charName;

    LoadedCell(String msg, String charName){
        this.cellText = msg;
        this.charName = charName;
    }

    /**
     * Show the cell's Delete Character button. Removes the cell's Create Character
     * button, since character already exists.
     *
     * @param createButton Button for character creation
     * @param deleteButton Button for character deletion
     */
    public void setCreateDeleteButtons(View createButton, View deleteButton){
    createButton.setVisibility(View.GONE);
    deleteButton.setVisibility(View.VISIBLE);
  }

    /**Returns the text for the inside of the cell
     *
     * @return Text for the cell, includes character's name, completed elections and elections won
     */
    public String getCellText(){return cellText;}


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
