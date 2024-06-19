package com.tkach360.evolutionsimulation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class GraphBotsAndTime {

    private static Stage window;

    public static void newWindow(String string) throws IOException {

        if (window != null) return;

        window = new Stage();
        Pane pane = new Pane();
        window.setTitle("Bots and Time");
        window.setScene(new Scene(pane, 600, 400));
        window.show();

        /*window = new Stage();

        Pane pane = new
        Scene scene = new Scene(pane, 300, 200);
        window.setScene(scene);
        window.setTitle(string);*/
    }
}