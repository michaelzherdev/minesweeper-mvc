package com.mzherdev.minesweeper.common;

/**
 * Helper class to get array of cells for the board
 */
public interface BoardGenerator {

    /**
     * Generate array of cells for the board
     * @return array of cells for the board
     */
    Cell[][] generate();
}
