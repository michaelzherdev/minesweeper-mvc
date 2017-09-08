package com.mzherdev.minesweeper.action;

import com.mzherdev.minesweeper.common.BoardGenerator;
import com.mzherdev.minesweeper.common.Cell;
import com.mzherdev.minesweeper.console.ConsoleBoard;
import com.mzherdev.minesweeper.console.ConsoleCell;
import com.mzherdev.minesweeper.logic.LogicImpl;
import org.junit.Test;

public class ActionImplTest {
    final ActionImpl action = new ActionImpl(
            new LogicImpl(), new ConsoleBoard(),
            new BoardGenerator() {
                @Override
                public Cell[][] generate() {
                    return new Cell[][] {
                            {new ConsoleCell(true), new ConsoleCell(false)},
                            {new ConsoleCell(true), new ConsoleCell(false)}};
                }
            }
    );

    @Test
    public void successGame() {
        action.initGame();
        action.select(0, 0, true);
        action.select(1, 0, true);
        action.select(0, 1, false);
        action.select(1, 1, false);
    }

    @Test
    public void failureGame() {
        action.initGame();
        action.select(0, 0, true);
        action.select(1, 0, false);
    }
}