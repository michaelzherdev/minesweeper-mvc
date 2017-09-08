package com.mzherdev.minesweeper.common;

/**
 * The root interface of user`s action for this game
 */
public interface UserAction {

    /**
     * Starts new game
     */
    void initGame();

    /**
     * Selects cell on position x, y.
     * @param x X-position of cell in array.
     * @param y Y-position of cell in array.
     * @param bomb <tt>true</tt> if user suggest that this cell contains bomb,
     *             <tt>false</tt> if user with to open this cell
     */
    void select(int x, int y, boolean bomb);
}
