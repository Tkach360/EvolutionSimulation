package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DefaultBotPainter implements IBotPaint {
    @Override
    public void drawBot(Bot bot, GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        Field bt = bot.getTile().getField();
        gc.fillRect(bt.getCx(), bt.getCy(), TileMap.TILE_SIDE, TileMap.TILE_SIDE);
        gc.setFill(bot.getColor());
        gc.fillRect(
                bt.getCx()+TileMap.EDGE_DISTANCE,
                bt.getCy()+TileMap.EDGE_DISTANCE,
                TileMap.TILE_SIDE - 2*TileMap.EDGE_DISTANCE,
                TileMap.TILE_SIDE - 2*TileMap.EDGE_DISTANCE
        );
    }
}
