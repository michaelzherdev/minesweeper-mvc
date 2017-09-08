package com.mzherdev.minesweeper.common;

/**
 * Description of logic of the game
 */
public interface Logic {

    /**
     * Set array of cell to logic class
     * @param cells Array of cells.
     */
    void loadBoard(Cell[][] cells);

    /**
     * Returns if this cell should bang and finish the game.
     * @param x X-position of cell in array.
     * @param y Y-position of cell in array.
     * @return
     */
    boolean shouldBang(int x, int y);

    /**
     * Returns if the game is finished or not
     * @return {@code true} if the game is finished,
     * otherwise {@code false}
     */
    boolean finish();

    /**
     * Describes whether user wish to open this cell or if user suggest
     * that this cell contains bomb.
     * @param x X-position of cell in array.
     * @param y Y-position of cell in array.
     * @param bomb <tt>true</tt> if user suggest that this cell contains bomb,
     *             <tt>false</tt> if user with to open this cell
     */
    void suspectBomb(int x, int y, boolean bomb);
}
