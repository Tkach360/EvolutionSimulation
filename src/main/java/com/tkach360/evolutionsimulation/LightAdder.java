package com.tkach360.evolutionsimulation;

public class LightAdder implements IMouseFunction{
    private int delta;

    public LightAdder(int delta) {
        this.delta = delta;
    }

    @Override
    public void doFunc(Tile tile) {
        tile.changeLighting(delta);
    }
}
