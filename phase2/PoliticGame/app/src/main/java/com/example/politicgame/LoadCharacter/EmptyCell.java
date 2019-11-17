package com.example.politicgame.LoadCharacter;

public class EmptyCell implements CellInfo {
    private final String BUTTONTEXT = "New";
    private final String CELLTEXT = "Press button to create character";

    public EmptyCell(){}

    /**Returns the text for the cell's button
     *
     * @return Text for the cell's button. Returns "New".
     */
    public String getButtonText(){return BUTTONTEXT;}


    /**Returns the text for the inside of the cell
     *
     * @return Text for the cell. Returns "Press button to create character".
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
