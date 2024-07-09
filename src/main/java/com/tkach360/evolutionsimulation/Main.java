package com.tkach360.evolutionsimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static List<Stage> childWindows = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1534, 800);
        stage.setTitle("Evolution Simulation");
        stage.setScene(scene);
        stage.setMaximized(true); // TODO: подумать над FullScreen
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            for(Stage chilsStage : childWindows){
                chilsStage.close();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

    public static void addChildWindow(Stage stage){
        childWindows.add(stage);
    }
}