package com.mzherdev.minesweeper.console;

import com.mzherdev.minesweeper.common.Cell;
import java.io.PrintStream;

public class ConsoleCell implements Cell<PrintStream> {
    private boolean bomb;
    private boolean suspectBomb = false;
    private boolean assumeEmpty = false;

    public ConsoleCell(boolean bomb) {
        this.bomb = bomb;
    }

    @Override
    public boolean isBomb() {
        return this.bomb;
    }

    @Override
    public boolean isBombSuspected() {
        return suspectBomb;
    }

    @Override
    public void suspectBomb() {
        this.suspectBomb = true;
    }

    @Override
    public boolean isAssumedEmpty() {
        return assumeEmpty;
    }

    @Override
    public void assumeEmpty() {
        assumeEmpty = true;
    }

    @Override
    public void draw(PrintStream paint, boolean real) {
        if (real) {
            if (this.bomb)
                paint.print("[*] ");
             else
                paint.print("[ ] ");
        } else {
            if (this.suspectBomb)
                paint.print("[?] ");
            else
                paint.print("[Х] ");
        }
    }
}
