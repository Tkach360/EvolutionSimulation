package com.tkach360.evolutionsimulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button pauseButton;

    @FXML
    private Label countTiksLabel;
    @FXML
    private Label timeSpeedLabel;

    private final double MIN_TIME_SPEED = 0.25;
    private final double DEFAULT_TIME_SPEED = 1.0;
    private final double MAX_TIME_SPEED = 32.0;

    private int countUpdate;

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

        timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                update();
                visorStrategy.drawAll();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        //timeline.play();

        countUpdate = 0;

        timeSpeedLabel.setText("x" + Double.toString(timeline.getCurrentRate()));
        countTiksLabel.setText(Integer.toString(countUpdate));
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
        updateTable();
    }

    private void updateTable(){
        countTiksLabel.setText(Integer.toString(++countUpdate));
    }

    @FXML
    private void onMouseClicked(MouseEvent e){
        int tX = (int)(e.getX() / TileMap.TILE_SIDE);
        int tY = (int)(e.getY() / TileMap.TILE_SIDE);

        /* если нажал не на tileMap, то ничего не происходит */
        if(tX >= TileMap.getInstance().getTiles().length || tY >= TileMap.getInstance().getTiles()[0].length) return;

        if(TileMap.getInstance().getTiles()[tX][tY].getAbstractTileObject() == null) {
            abstractTileObjects.add(new Bot(TileMap.getInstance().getTiles()[tX][tY], random));

            if(timeline.getStatus() != Animation.Status.RUNNING) timeline.play();
            System.out.println("добавил бота");
        }
        visorStrategy.drawAll();

        System.out.println("нажал");
    }

    @FXML
    private void pause(){
        if(timeline.getStatus() == Animation.Status.RUNNING){
            timeline.pause();
            pauseButton.setText("▶");
        }
        else {
            timeline.play();
            pauseButton.setText("||");
        }
    }

    @FXML
    private void slowedTime(){
        changeSpeed(0.5);
    }

    @FXML
    private void accelerationTime(){
        changeSpeed(2.0);
    }

    private void changeSpeed(double delta){
        double newRate = timeline.getCurrentRate() * delta;

        if(newRate < MIN_TIME_SPEED || newRate > MAX_TIME_SPEED){
            System.out.println("так");
            return;
        }

        timeSpeedLabel.setText("x" + Double.toString(newRate));
        timeline.setRate(newRate);
        timeline.play();
    }



}