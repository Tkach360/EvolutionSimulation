package com.tkach360.evolutionsimulation;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class VisibleArea {

    public final static int[][] UP = {{-1, 0, 1, -1, 0, 1, -1, 0, 1}, {-1, -1, -1, 0, 0, 0, 1, 1, 1}};
    public final static int[][] DOWN = {{1, 0, -1, 1, 0, -1, 1, 0, -1}, {1, 1, 1, 0, 0, 0, -1, -1, -1}};
    public final static int[][] LEFT = {{-1, -1, -1, 0, 0, 0, 1, 1, 1}, {1, 0, -1, 1, 0, -1, -1, -1, -1}};
    public final static int[][] RIGHT = {{1, 1, 1, 0, 0, 0, -1, -1, -1}, {-1, 0, 1, -1, 0, 1, -1, 0, 1}};

    private int[][] direction;

    public VisibleArea(int[][] direction) {
        this.direction = direction;
    }

    public VisibleArea(Random random) {
        setDirection(random);
    }

    public int[][] getDirection() {
        return direction;
    }

    public void setDirection(Random random){
        this.direction = switch (random.nextInt(4)){
            case 0 -> UP;
            case 1 -> DOWN;
            case 2 -> LEFT;
            case 3 -> RIGHT;
            default -> null;
        };
    }

    public void setDirection(int[][] direction) {
        this.direction = direction;
    }

    public Tile getTileInVisibleArea(int index, Tile thisTile){
        int tX = thisTile.getCx() + direction[0][index];
        int tY = thisTile.getCy() + direction[1][index];

        if(tX > TileMap.getInstance().getCountColumns() - 1) tX = 0;
        if(tY > TileMap.getInstance().getCountRows() - 1) tY = 0;
        if(tX < 0) tX = TileMap.getInstance().getCountColumns() - 1;
        if(tY < 0) tY = TileMap.getInstance().getCountRows() - 1;

        return TileMap.getInstance().getTiles()[tX][tY];
    }

    /** возвращает список тайлов поблизости
     * среди этих тайлов нет передаваемого тайла */
    public ArrayList<Tile> getTilesNear(Tile thisTile){
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        for(int i = 0; i < 9; i++){
            if(i != 4) {
                Tile tile = getTileInVisibleArea(i, thisTile);
                tiles.add(tile);
            }
        }

        return tiles;
    }
}
