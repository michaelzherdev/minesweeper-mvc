package com.mzherdev.minesweeper.common;

/**
 * Descript behaviour and parameters of single cell.
 * @param <T> canvas where cell should be drawn. For example, it
 *           can be PrintStream for console, Pane for JavaFX or
 *           Graphics for Swing respectively
 */
public interface Cell<T> {

    /**
     * Returns true if cell contains bomb, else false.
     * @return {@code true} if this cell contains bomb
     *         {@code false} if this cell doesn`t contain bomb
     */
    boolean isBomb();

    /**
     * Returns true if user suggest that cell contains bomb, else false.
     * @return {@code true} if user suggest that this cell contains bomb
     *         {@code false} if user suggest that this cell doesn`t contain bomb
     */
    boolean isBombSuspected();

    /**
     * Mark that user suggest that this cell contains bomb.
     */
    void suspectBomb();

    /**
     * Returns true if user suggest that this cell is empty.
     * @return {@code true} if user suggest that this cell is empty
     *         {@code false} if user not assumed that this sell is empty
     */
    boolean isAssumedEmpty();

    /**
     * mark that user suggest that this cell is empty
     */
    void assumeEmpty();

    /**
     * Draws cell on respective canvas.
     * @param paint canvas where cell should be drawn. For example, it
     *           can be PrintStream for console, Pane for JavaFX or
     *           Graphics for Swing respectively
     * @param openRealValue should real value of the cell shown or not
     */
    void draw(T paint, boolean openRealValue);
}
