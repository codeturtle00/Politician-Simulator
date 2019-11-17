package com.example.politicgame.LoadCharacter;

public interface CellInfo {
    /**Returns the text for the cell's button
     *
     * @return Text for the cell's button, either Delete or New
     */
    public String getButtonText();

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
