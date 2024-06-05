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

    private ArrayList<Bot> bots;
    private TileMap tileMap;
    private Color shadow = Color.rgb(0,0,0,0.6);

    public DefaultDrawStrategy(TileMap tileMap, ArrayList<Bot> bots, GraphicsContext gc) {
        this.tileMap = tileMap;
        this.bots = bots;
        this.gc = gc;
    }

    @Override
    public void drawAll() {
        for(Bot bot : bots){
            drawBot(bot);
        }
    }

    private void drawBot(Bot bot){
        gc.setFill(Color.BLACK);
        Tile bt = bot.getTile();
        gc.fillRect(bt.getCx(), bt.getCy(), TileMap.TILE_SIDE, TileMap.TILE_SIDE);
        gc.setFill(getBotColor(bot));
        gc.fillRect(bt.getCx()+TileMap.EDGE_DISTANCE, bt.getCy()+TileMap.EDGE_DISTANCE, TileMap.TILE_SIDE, TileMap.TILE_SIDE);
    }

    /*тут надо придумать логику*/
    private Color getBotColor(Bot bot){
        Color color = Color.GREEN;

        return color;
    }
}
