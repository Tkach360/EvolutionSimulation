package com.tkach360.evolutionsimulation;

import java.util.ArrayList;

public class BotsDeleter implements IMouseFunction{

    private int delWidth;

    public BotsDeleter(int delWidth){
        this.delWidth = delWidth;
    };

    @Override
    public void doFunc(Tile tile) {

        for(int i = -this.delWidth; i <= this.delWidth; i++){
            for(int j = -this.delWidth; j <= this.delWidth; j++){
                int cXdel = tile.getCx() + i;
                int cYdel = tile.getCy() + j;

                if(cXdel > TileMap.getInstance().getCountColumns() - 1) cXdel = TileMap.getInstance().getCountColumns() - 1;
                if(cYdel > TileMap.getInstance().getCountRows() - 1) cYdel = TileMap.getInstance().getCountRows() - 1;

                AbstractTileObject abs = TileMap.getInstance().getTiles()[cXdel][cYdel].getAbstractTileObject();
                if(abs instanceof Bot){
                    ((Bot)abs).die();
                    System.out.println("УБИЛ");
                } else {
                    System.out.println("пусто");
                }

            }
        }
    }
}
