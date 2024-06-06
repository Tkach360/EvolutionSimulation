package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.Canvas;

/** описывает поле тайлов */
public class TileMap {

    public static final double TILE_SIDE = 5; // размер тайла
    public static final double EDGE_DISTANCE = 0.5; // // TODO: подумать

    private int countTiles;
    private Canvas canvas;
    private Tile[][] tiles;

    public TileMap(Canvas canvas) {
        this.canvas = canvas;

        int countColumns = (int)(canvas.getWidth() / TILE_SIDE);
        int countRows = (int)(canvas.getHeight() / TILE_SIDE);

        this.tiles = new Tile[countColumns][countRows];

//      заполняем мировое поле тайлами
        for(int i=0;i<countColumns;i++){
            for(int j=0;j<countRows;j++){
                this.tiles[i][j] = new Tile(i*TILE_SIDE, j*TILE_SIDE, 2, 10); // TODO: изменить
            }
        }

        this.countTiles = countColumns * countRows;
    }

    public int getCountTiles() {
        return countTiles;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Tile[][] getTiles() {
        return tiles;
    }


}
