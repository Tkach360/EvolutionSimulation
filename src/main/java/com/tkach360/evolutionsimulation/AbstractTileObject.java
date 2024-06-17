package com.tkach360.evolutionsimulation;

/** абстрактный класс объекта, который может находиться в тайле */
public class AbstractTileObject {

    protected TypeTileObject type;
    protected Tile tile;

    public AbstractTileObject(Tile tile, TypeTileObject type) {
        this.tile = tile;
        this.type = type;
    }

    public TypeTileObject getType() {
        return type;
    }

    public Tile getTile() {
        return tile;
    }

    public void changeTile(Tile tile){
        this.tile.setAbstractTileObject(null);
        this.tile = tile;
        tile.setAbstractTileObject(this);
    }
}
