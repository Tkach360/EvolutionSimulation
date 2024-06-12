package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.Canvas;

/** описывает поле тайлов */
public class TileMap {

    private static TileMap instance;
    public static final double TILE_SIDE = 5; // размер тайла
    public static final double EDGE_DISTANCE = 0.5; // // TODO: подумать

    private Canvas canvas;
    private Tile[][] tiles;

    private TileMap(Canvas canvas) {
        this.canvas = canvas;

        int countColumns = (int)(canvas.getWidth() / TILE_SIDE);
        int countRows = (int)(canvas.getHeight() / TILE_SIDE);

        this.tiles = new Tile[countColumns][countRows];

//      заполняем мировое поле тайлами
        for(int i=0;i<countColumns;i++){
            for(int j=0;j<countRows;j++){
                this.tiles[i][j] = new Tile(i, j, 2, 10); // TODO: изменить
            }
        }
    }

    public static TileMap getInstance(Canvas canvas){
        if(instance == null) instance = new TileMap(canvas);
        return instance;
    }

    public static TileMap getInstance(){
        return instance;
    }

    public int getCountColumns() {
        return tiles.length;
    }

    public int getCountRows() {
        return tiles[0].length;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getCountTiles(){
        return tiles.length * tiles[0].length;
    }

}
