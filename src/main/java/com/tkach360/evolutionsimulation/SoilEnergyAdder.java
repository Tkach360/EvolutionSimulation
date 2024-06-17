package com.tkach360.evolutionsimulation;

public class SoilEnergyAdder implements IMouseFunction{
    private int delta;

    public SoilEnergyAdder(int delta) {
        this.delta = delta;
    }

    @Override
    public void doFunc(Tile tile) {
        tile.changeSoil(delta);
    }
}
