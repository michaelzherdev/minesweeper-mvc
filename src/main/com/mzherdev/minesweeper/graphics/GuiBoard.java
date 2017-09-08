package com.mzherdev.minesweeper.graphics;

import com.mzherdev.minesweeper.common.Board;
import com.mzherdev.minesweeper.common.Cell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.mzherdev.minesweeper.graphics.GuiBoardGenerator.FIELD_X_SIZE;
import static com.mzherdev.minesweeper.graphics.GuiBoardGenerator.FIELD_Y_SIZE;

public class GuiBoard extends Pane implements Board {

    public GuiBoard() {
        setPrefSize(FIELD_X_SIZE, FIELD_Y_SIZE);
    }

    private Cell[][] cells;

    @Override
    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public void drawBoard(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void drawCell(int x, int y) {
        if (cells[x][y].isBomb())
            cells[x][y].draw(this, true);
        else
            cells[x][y].draw(this, false);
    }

    @Override
    public void drawBang() {
        showMessage("*** BOMB HERE! YOU LOSE! ***");
    }

    @Override
    public void drawCongratulate() {
        showMessage("*** CONGRATULATION! YOU WIN! ***");
    }

    void showMessage(String message) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text(message)).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();
    }
}
