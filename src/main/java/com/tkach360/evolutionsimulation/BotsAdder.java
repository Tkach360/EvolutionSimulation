package com.tkach360.evolutionsimulation;

import java.util.Random;

public class BotsAdder implements IMouseFunction{

    private Random random;
    private final IBotsController botsController;

    public BotsAdder(IBotsController botsController, Random random) {
        this.botsController = botsController;
        this.random = random;
    }

    @Override
    public void doFunc(Tile tile) {
        if(tile.getAbstractTileObject() == null) {
            botsController.addBot(new Bot(tile, random), 0);
        }
    }
}
