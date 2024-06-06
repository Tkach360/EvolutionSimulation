package com.tkach360.evolutionsimulation;

import javafx.scene.paint.Color;

import java.util.Random;

/** класс бота ещё не завершен*/
public class Bot extends AbstractTileObject{

    public static final Color PHOTOSYNTHESIS_COLOR = Color.rgb(0, 210, 0);
    public static final Color PREDATION_COLOR = Color.rgb(210, 0, 0);
    public static final Color SOIL_COLOR = Color.rgb(0, 0, 210);

    private Color color; // бот получает цвет в зависимости от последнего источника энергии

    private int predation;
    private int photosynthesis;
    private int soil;

    public Bot(Tile tile, int predation, int photosynthesis, int soil) {
        this.tile = tile;
        this.predation = NumRangeController.setInRange(predation, 0, 4);
        this.photosynthesis = NumRangeController.setInRange(photosynthesis, 0, 4);
        this.soil = NumRangeController.setInRange(soil, 0, 4);
        this.color = PHOTOSYNTHESIS_COLOR;
    }

    // этот конструктор надо изменить так, чтобы подставлялись рандомные значения параметров
    public Bot(Tile tile, Random random){
        this.tile = tile;
        tile.setAbstractTileObject(this);
        this.predation = random.nextInt(5);
        this.photosynthesis = random.nextInt(5);
        this.soil = random.nextInt(5);
        this.color = PHOTOSYNTHESIS_COLOR;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
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
}
