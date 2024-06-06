package com.tkach360.evolutionsimulation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Canvas canvas;
    private Random random;

    private ArrayList<AbstractTileObject> abstractTileObjects;

    private TileMap tileMap;
    private AbstractVisorStrategy visorStrategy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        random = new Random(1);
        abstractTileObjects = new ArrayList<AbstractTileObject>();
        tileMap = new TileMap(canvas);
        visorStrategy = new DefaultVisorStrategy(canvas.getGraphicsContext2D(), tileMap, abstractTileObjects);

        visorStrategy.drawAll();
    }

    @FXML
    private void onMouseClicked(MouseEvent e){
        int tX = (int)(e.getX() / TileMap.TILE_SIDE);
        int tY = (int)(e.getY() / TileMap.TILE_SIDE);

        /* если нажал не на tileMap, то ничего не происходит */
        if(tX >= tileMap.getTiles().length || tY >= tileMap.getTiles()[0].length) return;

        if(tileMap.getTiles()[tX][tY].getAbstractTileObject() == null) {
            abstractTileObjects.add(new Bot(tileMap.getTiles()[tX][tY], random));

            System.out.println("добавил бота");
        }
        visorStrategy.drawAll();

        System.out.println("нажал");
    }
}