package com.tkach360.evolutionsimulation;

public class BotsDeleter implements IMouseFunction{

    @Override
    public void doFunc(Tile tile) {
        AbstractTileObject abs = tile.getAbstractTileObject();
        if(abs instanceof Bot){
            ((Bot)abs).delete();
        }
    }
}
