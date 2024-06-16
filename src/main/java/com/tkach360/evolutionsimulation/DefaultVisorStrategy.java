package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.GraphicsContext;

/** описывает стандартный режим рисования объектов на поле*/
public class DefaultVisorStrategy extends AbstractVisorStrategy {
    private IBotsController botsController;
    private IBotPaint botPainter;
    private ILightPaint lightPainter;

    public DefaultVisorStrategy(GraphicsContext gc, IBotsController botsController, TileMap tileMap, IBotPaint botPainter, ILightPaint lightPainter) {
        super(gc, tileMap);
        this.botsController = botsController;
        this.botPainter = botPainter;
        this.lightPainter = lightPainter;
    }

    @Override
    public void drawAll(){
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // рисуем ботов
        Bot bot = botsController.getFirst();
        while (bot != null){
            botPainter.drawBot(bot, gc);
            bot = botsController.getNextBot(bot);
        }

        // накладываем освещение
        for(Tile[] rowTiles : tileMap.getTiles()){
            for(Tile tile : rowTiles){
                lightPainter.drawLight(tile, gc);
            }
        }
    }
}
