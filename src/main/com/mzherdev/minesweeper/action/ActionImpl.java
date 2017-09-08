package com.mzherdev.minesweeper.action;

import com.mzherdev.minesweeper.common.*;

public class ActionImpl implements UserAction {
    protected final BoardGenerator generator;
    protected final Board board;
    protected final Logic logic;

    protected boolean shouldStartNewGame;

        public ActionImpl(final Logic logic, final Board board, final BoardGenerator generator) {
            this.generator = generator;
            this.board = board;
            this.logic = logic;
        }

        @Override
        public void initGame() {
            final Cell[][] cells = generator.generate();
            board.drawBoard(cells);
            logic.loadBoard(cells);
        }

        @Override
        public void select(int x, int y, boolean bomb) {
            logic.suspectBomb(x, y, bomb);
            board.drawCell(x, y);
            if (logic.shouldBang(x, y)) {
                board.drawBang();
                shouldStartNewGame = true;
            }
            if (logic.finish()) {
                board.drawCongratulate();
            }
        }
}
