package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/** описывает стандартный режим рисования объектов на поле*/
public class DefaultDrawStrategy extends AbstractDrawStrategy{

    private ArrayList<AbstractTileObject> abstractTileObjects;

    public DefaultDrawStrategy(GraphicsContext gc, TileMap tileMap, ArrayList<AbstractTileObject> abstractTileObjects) {
        super(gc, tileMap);
        this.abstractTileObjects = abstractTileObjects;
    }

    @Override
    public void drawAll() {

        // рисуем ботов
        for(AbstractTileObject abstractTileObjects : abstractTileObjects){
            if(abstractTileObjects instanceof Bot) drawBot((Bot)abstractTileObjects);
        }

        // накладываем освещение
        for(Tile[] rowTiles : tileMap.getTiles()){
            for(Tile tile : rowTiles){
                drawTileLight(tile);
            }
        }
    }

    private void drawBot(Bot bot){
        gc.setFill(Color.BLACK);
        Tile bt = bot.getTile();
        gc.fillRect(bt.getCx(), bt.getCy(), TileMap.TILE_SIDE, TileMap.TILE_SIDE);
        gc.setFill(bot.getColor());
        gc.fillRect(
                bt.getCx()+TileMap.EDGE_DISTANCE,
                bt.getCy()+TileMap.EDGE_DISTANCE,
                TileMap.TILE_SIDE - 2*TileMap.EDGE_DISTANCE,
                TileMap.TILE_SIDE - 2*TileMap.EDGE_DISTANCE
        );
    }

    private void drawTileLight(Tile tile){

        Color colorLight = Color.rgb(0, 0, 0, 0.1 * (4 - tile.getLighting()));
        gc.setFill(colorLight);
        gc.fillRect(tile.getCx(), tile.getCy(), TileMap.TILE_SIDE, TileMap.TILE_SIDE);
    }
}
