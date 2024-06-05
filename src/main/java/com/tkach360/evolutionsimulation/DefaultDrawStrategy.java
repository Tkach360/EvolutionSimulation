package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * класс еще не закончен
 * надо сделать логику получения цвета бота
 * сделать затемнение тайлов
 */
public class DefaultDrawStrategy extends AbstractDrawStrategy{

    private ArrayList<AbstractTileObject> abstractTileObjects;
    private TileMap tileMap;
    private Color shadow = Color.rgb(0,0,0,0.6);

    public DefaultDrawStrategy(TileMap tileMap, ArrayList<AbstractTileObject> abstractTileObjects, GraphicsContext gc) {
        this.tileMap = tileMap;
        this.abstractTileObjects = abstractTileObjects;
        this.gc = gc;
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
        gc.setFill(getBotColor(bot));
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

    /*тут надо придумать логику*/
    private Color getBotColor(Bot bot){
        Color color = Color.GREEN;

        return color;
    }
}
