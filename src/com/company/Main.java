/*
 * Ernestas Seminogovas
 * Prime factorisation with JavaFX GUI
 */

package com.company;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    @Override

    public void start(Stage primaryStage) throws Exception {
        AppView view = new AppView();
        view.setupUI(primaryStage);
        AppController controller = new AppController(view);
        view.setController(controller);
    }

}

