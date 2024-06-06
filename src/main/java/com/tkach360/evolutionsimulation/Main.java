package com.tkach360.evolutionsimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 600);
        stage.setTitle("Evolution Simulation");
        stage.setScene(scene);
        stage.setMaximized(true); // можно потом подумать над setFullScreen
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}