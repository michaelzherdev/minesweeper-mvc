package com.mzherdev.minesweeper.logic;

import com.mzherdev.minesweeper.common.Cell;
import com.mzherdev.minesweeper.common.Logic;

public class LogicImpl implements Logic {
    private Cell[][] cells;

    @Override
    public void loadBoard(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public boolean shouldBang(int x, int y) {
        final Cell selected = this.cells[x][y];
        return selected.isBomb() && !selected.isBombSuspected();
    }

    @Override
    public boolean finish() {
        boolean finish = false;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                finish = ((cell.isBombSuspected() && cell.isBomb()) ||
                        (cell.isAssumedEmpty() && !cell.isBomb()));
            }
        }
        return finish;
    }

    @Override
    public void suspectBomb(int x, int y, boolean bomb) {
        if (bomb) {
            this.cells[x][y].suspectBomb();
        } else {
            this.cells[x][y].assumeEmpty();
        }
    }
}
