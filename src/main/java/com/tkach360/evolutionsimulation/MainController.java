package com.tkach360.evolutionsimulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private VBox primeMenu;
    @FXML private Button pauseButton;

    private ToggleGroup wisorsToggle;
    @FXML private RadioButton RBsetDefaultVisor;
    @FXML private RadioButton RBsetSoilVisor;
    @FXML private RadioButton RBwithoutLightingVisor;

    private ToggleGroup functionsToggle;
    @FXML private RadioButton RBviewBot;
    @FXML private RadioButton RBviewTile;
    @FXML private RadioButton RBaddBot;
    @FXML private RadioButton RBdelBot;
    @FXML private RadioButton RBaddSoilEnergy;
    @FXML private RadioButton RBdelSoilEnergy;
    @FXML private RadioButton RBaddLight;
    @FXML private RadioButton RBdelLight;

    @FXML private Slider sliderWidthBrush;

    @FXML private Label countTiksLabel;
    @FXML private Label countBotsLabel;
    @FXML private Label timeSpeedLabel;
    @FXML private Label lastUpdateTimeLabel;

    @FXML private Spinner<Integer> SPenergyPerTik;
    @FXML private Spinner<Integer> SPminEnergyReproduction;
    @FXML private Spinner<Integer> SPmaxBotEnergy;

    private final double MIN_TIME_SPEED = 0.25;
    private final double DEFAULT_TIME_SPEED = 1.0;
    private final double MAX_TIME_SPEED = 32.0;
    private double currentTimeRate;

    private GraphBotsAndTime graphBotsAndTime;
    private GraphEdible graphEdible;

    @FXML private Canvas canvas;
    private Timeline timeline;
    private Random random;

    private UpdatableTileObjectsController botsController;
    private UpdateController updateController;
    private MouseFunctionController mouseFunction;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        random = new Random();
        currentTimeRate = 1.0;
        mouseFunction = new MouseFunctionController(1, new BotsAdder(botsController, random)); // slider для контроля за этим идеально подходит
        sliderWidthBrush.valueProperty().addListener((observable, oldValue, newValue) -> {
            mouseFunction.setWidthBrush(newValue.intValue());
        });

        SPenergyPerTik.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Bot.maxEnergy, Bot.energyPerTik));
        SPminEnergyReproduction.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Bot.maxEnergy, Bot.minEnergyReproduction));
        SPmaxBotEnergy.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, Bot.maxEnergy));

        SPenergyPerTik.valueProperty().addListener((observable, oldValue, newValue) -> {Bot.energyPerTik = newValue;});
        SPminEnergyReproduction.valueProperty().addListener((observable, oldValue, newValue) -> {Bot.minEnergyReproduction = newValue;});
        SPmaxBotEnergy.valueProperty().addListener((observable, oldValue, newValue) -> {
            Bot.maxEnergy = newValue;
            SPenergyPerTik.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Bot.maxEnergy, Bot.energyPerTik));
            SPminEnergyReproduction.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Bot.maxEnergy, Bot.minEnergyReproduction));
        });

        TileMap.getInstance(canvas);
        botsController = new UpdatableTileObjectsControllerWithArray(TileMap.getInstance().getCountTiles());

        updateController = new UpdateController(
                new DefaultVisorStrategy(
                        canvas.getGraphicsContext2D(),
                        botsController,
                        TileMap.getInstance(),
                        new DefaultBotPainter(),
                        new DefaultLightPainter()),
                botsController
        );

        timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /*if(updateController.getCountBots() == 0) {
                    timeline.pause();
                    pauseButton.setText("▶");
                    return;
                }*/
                if(updateController.getCount(TypeTileObject.Bot) == 0) pause();
                updateController.updateAll();
                updateTable();
                graphBotsAndTime.addData(updateController.getCountUpdate(), updateController.getCount(TypeTileObject.Bot));
                graphEdible.addData(
                        updateController.getCountEdible(EnergySource.PHOTOSYNTHESIS),
                        updateController.getCountEdible(EnergySource.PREDATION),
                        updateController.getCountEdible(EnergySource.SOIL)
                );
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        this.graphBotsAndTime = new GraphBotsAndTime(
                "График количества ботов от времени",
                700,
                500,
                1000,
                50,
                1000,
                100
        );

        this.graphEdible = new GraphEdible("График популярности источников энергии", 450, 300);

        initializeVisorsToggle();
        initializeFunctionsToggle();
        updateTable();
    }

    private void updateTable(){
        timeSpeedLabel.setText("x" + Double.toString(currentTimeRate));
        countTiksLabel.setText(Integer.toString(updateController.getCountUpdate()));
        countBotsLabel.setText(Integer.toString(updateController.getCount(TypeTileObject.Bot)));
        lastUpdateTimeLabel.setText(Long.toString(updateController.getLastUpdateTime()) + "ns");
    }

    @FXML
    private void onMouseClicked(MouseEvent e){
        int tX = (int)(e.getX() / TileMap.TILE_SIDE);
        int tY = (int)(e.getY() / TileMap.TILE_SIDE);

        /* если нажал не на tileMap, то ничего не происходит */
        if(tX >= TileMap.getInstance().getCountColumns() || tY >= TileMap.getInstance().getCountRows()) return;

        mouseFunction.changeTiles(TileMap.getInstance().getTiles()[tX][tY]);
        updateController.getVisorStrategy().drawAll();
        updateController.recalculateObjects();
        updateTable();
    }

    @FXML
    private void pause(){
        if(timeline.getStatus() == Animation.Status.RUNNING || updateController.getCountAllObjects() == 0){
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
        double newRate = currentTimeRate * delta;

        if(newRate < MIN_TIME_SPEED || newRate > MAX_TIME_SPEED) return;

        currentTimeRate = newRate;
        timeSpeedLabel.setText("x" + Double.toString(currentTimeRate));
        timeline.setRate(currentTimeRate);
    }

    private void initializeVisorsToggle(){
        wisorsToggle = new ToggleGroup();
        RBsetDefaultVisor.setToggleGroup(wisorsToggle);
        RBsetSoilVisor.setToggleGroup(wisorsToggle);
        RBwithoutLightingVisor.setToggleGroup(wisorsToggle);

        RBsetDefaultVisor.setOnAction(actionEvent -> {updateController.updateVisor(
                new DefaultVisorStrategy(
                        canvas.getGraphicsContext2D(),
                        botsController,
                        TileMap.getInstance(),
                        new DefaultBotPainter(),
                        new DefaultLightPainter()));});

        RBwithoutLightingVisor.setOnAction(actionEvent -> {updateController.updateVisor(
                new DefaultVisorStrategy(
                        canvas.getGraphicsContext2D(),
                        botsController,
                        TileMap.getInstance(),
                        new DefaultBotPainter(),
                        new WithoutLightPainter()));});

        RBsetSoilVisor.setOnAction(actionEvent -> {updateController.updateVisor(
                new SoilEnergyVisorStrategy(
                        canvas.getGraphicsContext2D(),
                        TileMap.getInstance()));});

        RBsetDefaultVisor.fire();
    }

    private void initializeFunctionsToggle(){
        functionsToggle = new ToggleGroup();
        RBaddBot.setToggleGroup(functionsToggle);
        RBdelBot.setToggleGroup(functionsToggle);
        RBaddLight.setToggleGroup(functionsToggle);
        RBdelLight.setToggleGroup(functionsToggle);
        RBaddSoilEnergy.setToggleGroup(functionsToggle);
        RBdelSoilEnergy.setToggleGroup(functionsToggle);
        RBviewBot.setToggleGroup(functionsToggle);
        RBviewTile.setToggleGroup(functionsToggle);

        RBaddBot.setOnAction(actionEvent -> mouseFunction.setMouseFunction(new BotsAdder(botsController, random)));
        RBdelBot.setOnAction(actionEvent -> mouseFunction.setMouseFunction(new BotsDeleter())); // TODO: изменить размер кисти
        RBaddLight.setOnAction(actionEvent -> mouseFunction.setMouseFunction(new LightAdder(1)));
        RBdelLight.setOnAction(actionEvent -> mouseFunction.setMouseFunction(new LightAdder(-1)));
        RBaddSoilEnergy.setOnAction(actionEvent -> mouseFunction.setMouseFunction(new SoilEnergyAdder(1)));
        RBdelSoilEnergy.setOnAction(actionEvent -> mouseFunction.setMouseFunction(new SoilEnergyAdder(-1)));

        RBaddBot.fire();
    }

    @FXML
    private void showGraphBotsAndTime() {
        this.graphBotsAndTime.show();
    }

    @FXML
    private void showGraphEdible() {
        this.graphEdible.show();
    }

}