package com.mzherdev.minesweeper.graphics;

import com.mzherdev.minesweeper.common.BoardGenerator;
import com.mzherdev.minesweeper.common.Cell;
import javafx.scene.layout.Pane;

import java.util.*;

public class GuiBoardGenerator implements BoardGenerator {

    public static final int FIELD_X_SIZE = 120;
    public static final int FIELD_Y_SIZE = 120;

    public static final int FIELD_CELL_SIZE = 40;

    public static final int FIELD_X_CELLS = FIELD_X_SIZE / FIELD_CELL_SIZE;
    public static final int FIELD_Y_CELLS = FIELD_Y_SIZE / FIELD_CELL_SIZE;

    private GuiBoard board;

    public GuiBoardGenerator(GuiBoard board) {
        this.board = board;
    }

    @Override
    public Cell[][] generate() {
        Cell<Pane>[][] cells = new Cell[FIELD_X_CELLS][FIELD_Y_CELLS];

        for (int y = 0; y < FIELD_X_CELLS; y++) {
            for (int x = 0; x < FIELD_Y_CELLS; x++) {
                GuiCell cell = new GuiCell(x, y, Math.random() < 0.2);
                cells[x][y] = cell;
                board.getChildren().add(cell);
            }
        }
        board.drawBoard(cells);

        for (int y = 0; y < FIELD_Y_CELLS; y++) {
            for (int x = 0; x < FIELD_X_CELLS; x++) {
                GuiCell cell = (GuiCell) board.getCells()[x][y];

                if (cell.isBomb()) continue;

                int bombCount = 0;
                for (Cell c : getNearestValues(cell)) {
                    if (c.isBomb())
                        bombCount++;
                }

                if (bombCount > 0)
                    cell.getText().setText(String.valueOf(bombCount));
            }
        }
        // for debug only
//        for (Cell[] cells1: cells) {
//            for (Cell c : cells1)
//                System.out.print(((GuiCell) c).getText().getText());
//            System.out.println();
//        }
        return cells;
    }

    private List<GuiCell> getNearestValues(GuiCell cell) {
        List<GuiCell> nearest = new ArrayList<>();

        int[] positions = new int[]{
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };

        for (int i = 0; i < positions.length; i++) {
            int dx = positions[i];
            int dy = positions[++i];

            int newX = cell.getX() + dx;
            int newY = cell.getY() + dy;

            if (newX < FIELD_X_CELLS && newX >= 0 &&
                    newY < FIELD_Y_CELLS && newY >= 0) {
                nearest.add((GuiCell) board.getCells()[newX][newY]);
            }
        }
        return nearest;
    }
}
