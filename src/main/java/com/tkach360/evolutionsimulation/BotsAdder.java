package com.tkach360.evolutionsimulation;

import java.util.Random;

public class BotsAdder implements IMouseFunction{

    private Random random;

    public BotsAdder() {
        this.random = new Random();
    }

    @Override
    public void doFunc(Tile tile) {
        if(tile.getAbstractTileObject() == null) {
            BotsController.getInstance().addBot(new Bot(tile, random), 0);
        }
    }
}
