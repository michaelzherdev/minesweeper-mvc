package com.mzherdev.minesweeper.console;

import com.mzherdev.minesweeper.common.Board;
import com.mzherdev.minesweeper.common.Cell;

public class ConsoleBoard implements Board {

    private Cell[][] cells;

    @Override
    public void drawBoard(Cell[][] cells) {
        this.cells = cells;
        redraw(false);
    }

    @Override
    public void drawCell(int x, int y) {
        System.out.println("*** SELECTED ***");
        redraw(false);
    }

    @Override
    public void drawBang() {
        System.out.println("*** BOMB HERE! YOU LOSE! ***");
        redraw(true);
    }

    @Override
    public void drawCongratulate() {
        System.out.println("*** CONGRATULATION! YOU WIN! ***");
    }

    private void redraw(boolean real) {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.draw(System.out, real);
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public Cell[][] getCells() {
        return cells;
    }
}
