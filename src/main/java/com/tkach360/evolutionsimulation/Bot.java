package com.tkach360.evolutionsimulation;

/** класс бота ещё не завершен*/
public class Bot extends AbstractTileObject{
    /** ссылка на тайл, в котором находится бот*/

    private int predation;
    private int photosynthesis;
    private int soil;

    public Bot(Tile tile, int predation, int photosynthesis, int soil) {
        this.tile = tile;
        this.predation = NumRangeController.setInRange(predation, 0, 4);
        this.photosynthesis = NumRangeController.setInRange(photosynthesis, 0, 4);
        this.soil = NumRangeController.setInRange(soil, 0, 4);
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
}
