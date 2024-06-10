package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/** описывает стратегию рисования энергии почвы на поле */
public class SoilEnergyVisorStrategy extends AbstractVisorStrategy {

    public SoilEnergyVisorStrategy(GraphicsContext gc, TileMap tileMap) {
        super(gc, tileMap);
    }

    @Override
    public void drawAll() {
        for(Tile[] rowTiles : tileMap.getTiles()){
            for(Tile tile : rowTiles){
                drawTile(tile);
            }
        }
    }

    private void drawTile(Tile tile){

        int r = 127 * (4 - tile.getSoilEnergy());
        int g = 63 * (4 - tile.getSoilEnergy());
        int b = 127 * (2 - tile.getSoilEnergy());

        if(r > 254) r = 254;
        if(b < 0) b = 0;

        gc.setFill(Color.rgb(r, g, b));
        gc.fillRect(tile.getField().getCx(), tile.getField().getCy(), TileMap.TILE_SIDE, TileMap.TILE_SIDE);
    }
}
