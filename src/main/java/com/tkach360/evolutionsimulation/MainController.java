package com.tkach360.evolutionsimulation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Canvas canvas;

    private ArrayList<Bot> bots;
    private TileMap tileMap;
    private AbstractDrawStrategy drawStrategy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bots = new ArrayList<Bot>();
        tileMap = new TileMap(canvas);
        drawStrategy = new DefaultDrawStrategy(tileMap, bots, canvas.getGraphicsContext2D());
    }

    @FXML
    private void onMouseClicked(MouseEvent e){
        int tX = (int)(e.getX() / TileMap.TILE_SIDE);
        int tY = (int)(e.getY() / TileMap.TILE_SIDE);

        bots.add(new Bot(tileMap.getTiles()[tX][tY]));
        repaint();

        System.out.println("нажал");
    }

    private void repaint(){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawStrategy.drawAll();

        System.out.println("перерисоавл");
    }
}