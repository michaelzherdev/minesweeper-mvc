package com.mzherdev.minesweeper;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;

public class Controller implements EventHandler {
    private Model model;

    public Controller(View view) {
        model = new Model(view);
    }

    public Parent createContent() {
       return model.createContent();
    }

    public void showMessage(String message) {
        model.showMessage(message);
    }


    @Override
    public void handle(Event event) {
        Model.Cell cell = (Model.Cell) event.getSource();
        cell.open();
    }
}
