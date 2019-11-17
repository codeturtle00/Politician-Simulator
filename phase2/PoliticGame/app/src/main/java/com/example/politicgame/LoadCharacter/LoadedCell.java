package com.example.politicgame.LoadCharacter;

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
