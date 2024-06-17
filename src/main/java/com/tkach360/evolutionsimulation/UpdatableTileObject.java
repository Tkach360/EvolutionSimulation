package com.tkach360.evolutionsimulation;

public abstract class UpdatableTileObject extends AbstractTileObject {

    /** установка этого параметра происходит при регитрауии объекта в UpdatableTileObjectsController */
    protected UpdatableObjectNode updatableObjectNode;

    public UpdatableTileObject(Tile tile, TypeTileObject type) {
        super(tile, type);
    }

    public abstract void update();

    public UpdatableObjectNode getUpdatableObjectNode() {
        return updatableObjectNode;
    }

    public void setUpdatableObjectNode(UpdatableObjectNode updatableObjectNode) {
        this.updatableObjectNode = updatableObjectNode;
    }
}
