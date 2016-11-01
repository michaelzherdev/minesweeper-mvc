package com.mzherdev.minesweeper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public static final int FIELD_X_SIZE = 400;
    public static final int FIELD_Y_SIZE = 400;

    public static final int FIELD_CELL_SIZE = 40;

    public static final int FIELD_X_CELLS = FIELD_X_SIZE / FIELD_CELL_SIZE;
    public static final int FIELD_Y_CELLS = FIELD_Y_SIZE / FIELD_CELL_SIZE;

    private Cell[][] grid = new Cell[FIELD_X_CELLS][FIELD_Y_CELLS];

    private View view;

    Cell[][] getGrid() {
        return grid;
    }

    int totalBombCount = 0;

    public Model(View view) {
        this.view = view;
    }

    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(FIELD_X_SIZE, FIELD_Y_SIZE);

        int bc = 0;

        for (int y = 0; y < FIELD_X_CELLS; y++) {
            for (int x = 0; x < FIELD_Y_CELLS; x++) {
                Cell cell = new Cell(x, y, Math.random() < 0.2);
                grid[x][y] = cell;
                if(cell.isBombHere)
                    totalBombCount++;
                root.getChildren().add(cell);
            }
        }

        for (int y = 0; y < FIELD_Y_CELLS; y++) {
            for (int x = 0; x < FIELD_X_CELLS; x++) {
                Cell cell = getGrid()[x][y];

                if (cell.isBombHere)
                    continue;

                int bombCount = 0;

                for (Cell c : getNearestValues(cell)) {
                    if (c.isBombHere) bombCount++;
                }
                if (bombCount > 0)
                    cell.text.setText(String.valueOf(bombCount));
            }
        }
        return root;
    }

    private List<Cell> getNearestValues(Cell cell) {
        List<Cell> nearest = new ArrayList<>();

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

            int newX = cell.x + dx;
            int newY = cell.y + dy;

            if (newX < FIELD_X_CELLS && newX >= 0 &&
                    newY < FIELD_Y_CELLS && newY >= 0) {
                nearest.add(getGrid()[newX][newY]);
            }
        }
        return nearest;
    }

    public void showMessage(String message) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text(message)).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();
    }

    private boolean isWin() {
        int closedCellsLeft = 0;
        for (int y = 0; y < FIELD_X_CELLS; y++) {
            for (int x = 0; x < FIELD_Y_CELLS; x++) {
                if(!grid[x][y].isOpen) {
                    closedCellsLeft++;
                }
            }
        }
        return closedCellsLeft == totalBombCount;
    }


    public class Cell extends StackPane {

        int x;
        int y;
        boolean isBombHere;
        boolean isOpen = false;

        private Rectangle border = new Rectangle(Model.FIELD_CELL_SIZE - 2, Model.FIELD_CELL_SIZE - 2, Color.GRAY);
        private Text text = new Text();

        Cell(int x, int y, boolean isBombHere) {
            this.x = x;
            this.y = y;
            this.isBombHere = isBombHere;

            border.setStroke(Color.LIGHTBLUE);
            text.setText(isBombHere ? "X" : "0");
            text.setVisible(false);
            getChildren().addAll(text, border);

            setTranslateX(x * Model.FIELD_CELL_SIZE);
            setTranslateY(y * Model.FIELD_CELL_SIZE);

            setOnMouseClicked(view.getController());
        }

        public void open() {
            if (isOpen) return;

            text.setVisible(true);
            border.setFill(null);

            if (isBombHere) {
                view.newGame();
                view.showMessage("You lose! Bombs left " + --totalBombCount);
                return;
            }
            isOpen = true;

            if(isWin()) {
                showMessage("You win!");
            }
        }
    }


}
