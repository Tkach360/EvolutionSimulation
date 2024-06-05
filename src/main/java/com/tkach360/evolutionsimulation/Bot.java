package com.tkach360.evolutionsimulation;

/** класс бота ещё не завершен*/
public class Bot{
    /** ссылка на тайл, в котором находится бот*/
    private Tile tile;

    private int predation;
    private int photosynthesis;
    private int soil;

    public Bot(Tile tile, int predation, int photosynthesis, int soil) {
        this.tile = tile;
        this.predation = predation;
        this.photosynthesis = photosynthesis;
        this.soil = soil;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getPredation() {
        return predation;
    }

    public void setPredation(int predation) {
        this.predation = predation;
    }

    public int getPhotosynthesis() {
        return photosynthesis;
    }

    public void setPhotosynthesis(int photosynthesis) {
        this.photosynthesis = photosynthesis;
    }

    public int getSoil() {
        return soil;
    }

    public void setSoil(int soil) {
        this.soil = soil;
    }
}
