package com.tkach360.evolutionsimulation;

import java.util.ArrayList;

public class BotsDeleter implements IMouseFunction{

    public BotsDeleter(){};

    @Override
    public void doFunc(Tile tile) {
        AbstractTileObject abs = tile.getAbstractTileObject();;
        if(abs instanceof Bot){
            ((Bot)abs).die();
        }
    }
}
