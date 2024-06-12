package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.Random;

/** класс бота
 * основной класс проекта*/
public class Bot extends AbstractTileObject{

    /** устанавливается если последним источником энергии бота был фотосинтез */
    public static final Color PHOTOSYNTHESIS_COLOR = Color.rgb(0, 210, 0);

    /** устанавливается если последним источником энергии бота было хищничество */
    public static final Color PREDATION_COLOR = Color.rgb(210, 0, 0);

    /** устанавливается если последним источником энергии бота была энергия почвы */
    public static final Color SOIL_COLOR = Color.rgb(0, 0, 210);

    private Color color; // бот получает цвет в зависимости от последнего источника энергии

    private VisibleArea visibleArea;
    private int predation;
    private int photosynthesis;
    private int soil;

    public Bot(Tile tile, int[][] direction, int predation, int photosynthesis, int soil) {
        this.tile = tile;
        this.visibleArea = new VisibleArea(direction);
        this.predation = NumRangeController.setInRange(predation, 0, 4);
        this.photosynthesis = NumRangeController.setInRange(photosynthesis, 0, 4);
        this.soil = NumRangeController.setInRange(soil, 0, 4);
        this.color = PHOTOSYNTHESIS_COLOR;
    }

    public Bot(Tile tile, Random random){
        this.tile = tile;
        this.visibleArea = new VisibleArea(random);
        tile.setAbstractTileObject(this);
        this.predation = random.nextInt(5);
        this.photosynthesis = random.nextInt(5);
        this.soil = random.nextInt(5);
        this.color = PHOTOSYNTHESIS_COLOR;
    }

    private void moveForward(){
        Tile tileForward = visibleArea.getTileInVisibleArea(1, this.tile);
        if(tileForward.getAbstractTileObject() == null){
            setTile(tileForward);
        }
    }

    // TODO: этот метод отвечает за принятие решения о действии и собственно действии
    // index необходим для реализации размножения
    public void doSomething(int index){
        if (getVisibleArea().getTileInVisibleArea(1, getTile()).getAbstractTileObject() != null) getVisibleArea().setDirection(new Random()); // TODO: random убрать он для тестов
        moveForward();
    }

    public void rotate(int[][] visibleArea) {
        this.visibleArea = new VisibleArea(visibleArea);
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile.setAbstractTileObject(null);
        this.tile = tile;
        tile.setAbstractTileObject(this);
    }

    public int getPredation() {
        return predation;
    }

    public void setPredation(int predation) {
        this.predation = NumRangeController.setInRange(predation, 0, 4);
    }

    public int getPhotosynthesis() {
        return photosynthesis;
    }

    public void setPhotosynthesis(int photosynthesis) {
        this.photosynthesis = NumRangeController.setInRange(photosynthesis, 0, 4);
    }

    public int getSoil() {
        return soil;
    }

    public void setSoil(int soil) {
        this.soil = NumRangeController.setInRange(soil, 0, 4);;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public VisibleArea getVisibleArea() {
        return visibleArea;
    }

    public void setVisibleArea(VisibleArea visibleArea) {
        this.visibleArea = visibleArea;
    }

    // TODO: добавить стандартные методы
}
