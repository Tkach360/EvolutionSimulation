package com.tkach360.evolutionsimulation;

import java.util.Random;

public class BotsAdder implements IMouseFunction{

    private Random random;
    private final UpdatableTileObjectsController updatableTileObjectsController;

    public BotsAdder(UpdatableTileObjectsController updatableTileObjectsController, Random random) {
        this.updatableTileObjectsController = updatableTileObjectsController;
        this.random = random;
    }

    @Override
    public void doFunc(Tile tile) {
        if(tile.getAbstractTileObject() == null) {
            updatableTileObjectsController.add(new Bot(tile, random), 0);
        }
    }
}
