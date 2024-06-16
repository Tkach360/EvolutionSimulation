package com.tkach360.evolutionsimulation;

import java.util.Random;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Direction getRandom(Random random){
        return switch (random.nextInt(4)) {
            case 0 -> UP;
            case 1 -> DOWN;
            case 2 -> LEFT;
            case 3 -> RIGHT;
            default -> null;
        };
    }

    public static int[][] getArrayVisibleAreaBias(Direction direction){
        return switch (direction){
            case UP -> new int[][]{{-1, 0, 1, -1, 0, 1, -1, 0, 1}, {-1, -1, -1, 0, 0, 0, 1, 1, 1}};
            case DOWN -> new int[][]{{1, 0, -1, 1, 0, -1, 1, 0, -1}, {1, 1, 1, 0, 0, 0, -1, -1, -1}};
            case LEFT -> new int[][]{{-1, -1, -1, 0, 0, 0, 1, 1, 1}, {1, 0, -1, 1, 0, -1, -1, -1, -1}};
            case RIGHT -> new int[][]{{1, 1, 1, 0, 0, 0, -1, -1, -1}, {-1, 0, 1, -1, 0, 1, -1, 0, 1}};
        };
    }
}
