package com.tkach360.evolutionsimulation;

import javafx.scene.canvas.GraphicsContext;

/** описывает стандартный режим рисования объектов на поле*/
public class DefaultVisorStrategy extends AbstractVisorStrategy {
    private UpdatableTileObjectsController updatableTileObjectsController;
    private IBotPaint botPainter;
    private ILightPaint lightPainter;

    public DefaultVisorStrategy(GraphicsContext gc, UpdatableTileObjectsController updatableTileObjectsController, TileMap tileMap, IBotPaint botPainter, ILightPaint lightPainter) {
        super(gc, tileMap);
        this.updatableTileObjectsController = updatableTileObjectsController;
        this.botPainter = botPainter;
        this.lightPainter = lightPainter;
    }

    @Override
    public void drawAll(){
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // рисуем ботов
        UpdatableTileObject obj = updatableTileObjectsController.getFirst();
        while (obj != null){
            if(obj.getType() == TypeTileObject.Bot) botPainter.drawBot((Bot)obj, gc);
            obj = updatableTileObjectsController.getNext(obj);
        }

        // накладываем освещение
        for(Tile[] rowTiles : tileMap.getTiles()){
            for(Tile tile : rowTiles){
                lightPainter.drawLight(tile, gc);
            }
        }
    }
}
