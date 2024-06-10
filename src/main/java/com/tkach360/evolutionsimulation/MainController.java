package com.tkach360.evolutionsimulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private VBox primeMenu;

    @FXML
    private Canvas canvas;
    private Random random;
    private Timeline timeline;

    private ArrayList<AbstractTileObject> abstractTileObjects;

    private AbstractVisorStrategy visorStrategy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        random = new Random(1);
        abstractTileObjects = new ArrayList<AbstractTileObject>();
        TileMap.getInstance(canvas);
        visorStrategy = new DefaultVisorStrategy(canvas.getGraphicsContext2D(), TileMap.getInstance(), abstractTileObjects);

        timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                update();
                visorStrategy.drawAll();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // TODO: это тестовый update
    private void update(){
        for(AbstractTileObject bot : abstractTileObjects){
            if(bot instanceof Bot){
                Bot b = (Bot) bot;
                if (b.getVisibleArea().getTileInVisibleArea(1, b.getTile()).getAbstractTileObject() != null) b.getVisibleArea().setDirection(random);
                b.moveForward();
            }
        }
    }

    @FXML
    private void onMouseClicked(MouseEvent e){
        int tX = (int)(e.getX() / TileMap.TILE_SIDE);
        int tY = (int)(e.getY() / TileMap.TILE_SIDE);

        /* если нажал не на tileMap, то ничего не происходит */
        if(tX >= TileMap.getInstance().getTiles().length || tY >= TileMap.getInstance().getTiles()[0].length) return;

        if(TileMap.getInstance().getTiles()[tX][tY].getAbstractTileObject() == null) {
            abstractTileObjects.add(new Bot(TileMap.getInstance().getTiles()[tX][tY], random));

            System.out.println("добавил бота");
        }
        visorStrategy.drawAll();

        System.out.println("нажал");
    }



}