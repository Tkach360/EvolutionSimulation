package com.tkach360.evolutionsimulation;

public class LightAdder implements IMouseFunction{
    private int width;
    private int delta;

    public LightAdder(int width, int delta) {
        this.delta = delta;
        this.width = width;
    }

    @Override
    public void doFunc(Tile tile) {
        for(int i = -this.width; i <= this.width; i++){
            for(int j = -this.width; j <= this.width; j++){
                int cX = tile.getCx() + i;
                int cY = tile.getCy() + j;

                if(cX > TileMap.getInstance().getCountColumns() - 1) cX = TileMap.getInstance().getCountColumns() - 1;
                if(cY > TileMap.getInstance().getCountRows() - 1) cY = TileMap.getInstance().getCountRows() - 1;

                TileMap.getInstance().getTiles()[cX][cY].changeLighting(delta);

                System.out.println("+ свет");
            }
        }
    }
}
