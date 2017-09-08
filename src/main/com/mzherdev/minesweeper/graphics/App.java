package com.mzherdev.minesweeper.graphics;

import com.mzherdev.minesweeper.logic.LogicImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] arg) {
        launch(arg);
    }

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
            GuiBoard board = new GuiBoard();
            GuiAction action = new GuiAction(
                    new LogicImpl(), board,
                    new GuiBoardGenerator(board));
            action.initGame();

            primaryStage.setTitle("Minesweeper");
            primaryStage.setScene(new Scene(board));
            stage = primaryStage;
            primaryStage.show();
    }

    public static void newGame() {
        App app = new App();
        try {
            app.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
