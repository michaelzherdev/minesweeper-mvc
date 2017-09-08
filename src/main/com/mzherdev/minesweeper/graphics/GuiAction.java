package com.mzherdev.minesweeper.graphics;

import com.mzherdev.minesweeper.action.ActionImpl;
import com.mzherdev.minesweeper.common.Board;
import com.mzherdev.minesweeper.common.BoardGenerator;
import com.mzherdev.minesweeper.common.Logic;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import static com.mzherdev.minesweeper.graphics.GuiBoardGenerator.FIELD_X_CELLS;
import static com.mzherdev.minesweeper.graphics.GuiBoardGenerator.FIELD_Y_CELLS;

public class GuiAction extends ActionImpl implements EventHandler {


    public GuiAction(Logic logic, Board board, BoardGenerator generator) {
        super(logic, board, generator);
    }

    @Override
    public void handle(Event event) {
     GuiCell cell = (GuiCell) event.getSource();
     select(cell.getX(), cell.getY(), cell.isBomb());

     if(shouldStartNewGame)
         App.newGame();
    }

    @Override
    public void initGame() {
        super.initGame();

        for (int y = 0; y < FIELD_X_CELLS; y++) {
            for (int x = 0; x < FIELD_Y_CELLS; x++) {
                GuiCell cell = (GuiCell) board.getCells()[x][y];
                cell.setOnMouseClicked(this);
                cell.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    if (MouseButton.SECONDARY == e.getButton()) {
                        cell.suspectBomb();
                        cell.assumeEmpty();
                    }
                    select(cell.getX(), cell.getY(), cell.isBombSuspected());
                    e.consume();
                });
            }
        }
    }
}
