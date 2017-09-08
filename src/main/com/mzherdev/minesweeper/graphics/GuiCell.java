package com.mzherdev.minesweeper.graphics;

import com.mzherdev.minesweeper.common.Cell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static com.mzherdev.minesweeper.graphics.GuiBoardGenerator.FIELD_CELL_SIZE;

public class GuiCell extends StackPane implements Cell<Pane> {

    private boolean bomb;
    private boolean suspectBomb = false;
    private boolean assumeEmpty = false;
    private int x;
    private int y;

    GuiCell(int x, int y, boolean bomb) {
        this.x = x;
        this.y = y;
        this.bomb = bomb;

        border.setStroke(Color.LIGHTBLUE);
        text.setText(bomb ? "X" : "0");
        text.setVisible(false);
        getChildren().addAll(text, border);

        setTranslateX(x * FIELD_CELL_SIZE);
        setTranslateY(y * FIELD_CELL_SIZE);
    }

    private Rectangle border = new Rectangle(FIELD_CELL_SIZE - 2, FIELD_CELL_SIZE - 2, Color.GRAY);
    private Text text = new Text();

    @Override
    public boolean isBomb() {
        return bomb;
    }

    @Override
    public boolean isBombSuspected() {
        return suspectBomb;
    }

    @Override
    public void suspectBomb() {
        suspectBomb = true;
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
    public void draw(Pane paint, boolean real) {
        if (suspectBomb)
            text.setText("?");
        text.setVisible(true);
        border.setFill(null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Text getText() {
        return text;
    }
}
