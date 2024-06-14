package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DefaultLightPainter implements ILightPaint{
    @Override
    public void drawLight(Tile tile, GraphicsContext gc) {
        Color colorLight = Color.rgb(0, 0, 0, 0.1 * (4 - tile.getLighting()));
        gc.setFill(colorLight);
        gc.fillRect(tile.getField().getCx(), tile.getField().getCy(), TileMap.TILE_SIDE, TileMap.TILE_SIDE);
    }
}
