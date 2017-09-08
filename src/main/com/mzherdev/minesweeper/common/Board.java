package com.mzherdev.minesweeper.common;

/**
 * Board`s behaviour description
 */
public interface Board {

    /**
     * Draws board for given array of cells
     * @param cells Array of cells.
     */
    void drawBoard(Cell[][] cells);

    /**
     * Draw cell
     * @param x X-position of cell in array.
     * @param y Y-position of cell in array.
     */
    void drawCell(int x, int y);

    /**
     * Draws bombs explosure or shows message that user losed
     */
    void drawBang();

    /**
     * Draws congratulation animation or shows message that user win
     */
    void drawCongratulate();

    /**
     * Returns array of cells
     * @return array of cells of the board
     */
    Cell[][] getCells();
}
