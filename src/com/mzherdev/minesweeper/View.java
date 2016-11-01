package com.mzherdev.minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {

    Scene scene;
    Stage stage;
    Controller controller = new Controller(this);

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(controller.createContent());
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showMessage(String message) {
        controller.showMessage(message);
    }

    public void newGame() {
        scene = new Scene(controller.createContent());
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
    }

    public Controller getController() {
        return controller;
    }
}
