package com.tkach360.evolutionsimulation;

/** класс бота ещё не завершен*/
public class Bot extends AbstractTileObject{
    /** ссылка на тайл, в котором находится бот*/

    private int predation;
    private int photosynthesis;
    private int soil;

    public Bot(Tile tile, int predation, int photosynthesis, int soil) {
        this.tile = tile;
        this.predation = predation;
        this.photosynthesis = photosynthesis;
        this.soil = soil;
    }

    // этот конструктор надо изменить так, чтобы подставлялись рандомные значения параметров
    public Bot(Tile tile){
        this.tile = tile;
        tile.setAbstractTileObject(this);
        this.predation = 5;
        this.photosynthesis = 5;
        this.soil = 5;
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
