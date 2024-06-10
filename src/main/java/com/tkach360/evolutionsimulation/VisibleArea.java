package com.tkach360.evolutionsimulation;

import javafx.util.Pair;

import java.util.Random;

public class VisibleArea {

    public final static int[][] UP = {{-1, 0, 1, -1, 0, 1}, {-1, -1, -1, 0, 0, 0}};
    public final static int[][] DOWN = {{1, 0, -1, 1, 0, -1}, {1, 1, 1, 0, 0, 0}};
    public final static int[][] LEFT = {{-1, -1, -1, 0, 0, 0}, {1, 0, -1, 1, 0, -1}};
    public final static int[][] RIGHT = {{1, 1, 1, 0, 0, 0}, {-1, 0, 1, -1, 0, 1}};

    private int[][] direction;

    public VisibleArea(int[][] direction) {
        this.direction = direction;
    }

    public VisibleArea(Random random) {
        this.direction = switch (random.nextInt(4)){
            case 0 -> UP;
            case 1 -> DOWN;
            case 2 -> LEFT;
            case 3 -> RIGHT;
            default -> null;
        };
    }

    public int[][] getDirection() {
        return direction;
    }

    public void setDirection(int[][] direction) {
        this.direction = direction;
    }

    public Pair<Integer, Integer> getTileInVisibleArea(int index, Tile thisTile){
        int tX = thisTile.getCx() + direction[0][index];
        int tY = thisTile.getCy() + direction[1][index];

        return new Pair<Integer, Integer>(tX, tY);
    }
}
