package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * абстрактный класс для стратегии рисования
 * используется ка основа для режимов отрисовки
 */
public abstract class AbstractDrawStrategy {

    protected GraphicsContext gc;
    protected TileMap tileMap;

    public AbstractDrawStrategy(GraphicsContext gc, TileMap tileMap) {
        this.gc = gc;
        this.tileMap = tileMap;
    }

    /*** рисует все объекты в  соответствии со стратегией рисования*/
    public abstract void drawAll();

/*    protected void drawNet(){
        System.out.println("рисую сетку");
        for(Tile[] rowTiles : tileMap.getTiles()){
            for(Tile tile : rowTiles){
                drawTileBorder(tile);
            }
        }
    }*/

/*    protected void drawTileBorder(Tile tile){

        gc.setFill(Color.BLACK);
        gc.setLineWidth(0.5);

        gc.beginPath();
        gc.moveTo(tile.getCx(), tile.getCy() + 1);
        gc.lineTo(tile.getCx(), tile.getCy());
        gc.lineTo(tile.getCx() + 1, tile.getCy());

        gc.moveTo(tile.getCx() + TileMap.TILE_SIDE - 1, tile.getCy());
        gc.lineTo(tile.getCx() + TileMap.TILE_SIDE, tile.getCy());
        gc.lineTo(tile.getCx() + TileMap.TILE_SIDE, tile.getCy() - 1);

        gc.moveTo(tile.getCx() + TileMap.TILE_SIDE, tile.getCy() + TileMap.TILE_SIDE - 1);
        gc.lineTo(tile.getCx() + TileMap.TILE_SIDE, tile.getCy() + TileMap.TILE_SIDE);
        gc.lineTo(tile.getCx() + TileMap.TILE_SIDE - 1, tile.getCy()  + TileMap.TILE_SIDE);

        gc.moveTo(tile.getCx() + 1, tile.getCy() + TileMap.TILE_SIDE);
        gc.lineTo(tile.getCx(), tile.getCy() + TileMap.TILE_SIDE);
        gc.lineTo(tile.getCx(), tile.getCy() + TileMap.TILE_SIDE - 1);
        gc.stroke();
    }*/



}
