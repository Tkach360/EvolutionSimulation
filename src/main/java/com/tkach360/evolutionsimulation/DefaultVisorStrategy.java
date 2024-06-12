package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/** описывает стандартный режим рисования объектов на поле*/
public class DefaultVisorStrategy extends AbstractVisorStrategy {

    public DefaultVisorStrategy(GraphicsContext gc, TileMap tileMap) {
        super(gc, tileMap);
    }

    @Override
    public void drawAll() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // рисуем ботов
        int index = BotsController.getInstance().getNextBotNodeIndex(0);
        while(index != 0){
            drawBot(BotsController.getInstance().getBot(index));
            index = BotsController.getInstance().getNextBotNodeIndex(index);
        }

        // накладываем освещение
        for(Tile[] rowTiles : tileMap.getTiles()){
            for(Tile tile : rowTiles){
                drawTileLight(tile);
            }
        }

        System.out.println("перерисовал");
    }

    private void drawBot(Bot bot){
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

    private void drawTileLight(Tile tile){

        Color colorLight = Color.rgb(0, 0, 0, 0.1 * (4 - tile.getLighting()));
        gc.setFill(colorLight);
        gc.fillRect(tile.getField().getCx(), tile.getField().getCy(), TileMap.TILE_SIDE, TileMap.TILE_SIDE);
    }
}
