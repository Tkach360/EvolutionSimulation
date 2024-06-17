package com.tkach360.evolutionsimulation;

import java.util.HashMap;

public class UpdateController {

    private final HashMap<TypeTileObject, Integer> tableCount;
    private int countUpdate;
    private long lastUpdateTime;
    private AbstractVisorStrategy visorStrategy;
    private final UpdatableTileObjectsController updatableObjectsController;

    public UpdateController(AbstractVisorStrategy visorStrategy, UpdatableTileObjectsController updatableObjectsController) {
        this.updatableObjectsController = updatableObjectsController;
        this.visorStrategy = visorStrategy;
        this.lastUpdateTime = 0;
        this.countUpdate = 0;
        this.tableCount = new HashMap<>();

        for(TypeTileObject type : TypeTileObject.values()){
            tableCount.put(type, 0);
        }
    }

/*    public void updateValues(){
        int newCountBots = 0;
        UpdatableTileObject obj = updatableObjectsController.getFirst();
        while (obj != null){
            if(obj.getType() == TypeTileObject.Bot) newCountBots++; // TODO: поставить switch при появлении новых TileObjects
            obj = updatableObjectsController.getNext(obj);
        }
        countBots = newCountBots;
    }*/

    public void updateAll(){
        countUpdate++;
        long startUpdateTime = System.nanoTime();

        updateObjects();

        long endUpdateTime = System.nanoTime();
        lastUpdateTime = endUpdateTime - startUpdateTime;

        //updateValues();
        visorStrategy.drawAll();
    }

    public void updateVisor(AbstractVisorStrategy newVisorStrategy){
        setVisorStrategy(newVisorStrategy);
        this.visorStrategy.drawAll();
    }

    private void updateObjects(){
        for(TypeTileObject type : TypeTileObject.values()) tableCount.put(type, 0);

        UpdatableTileObject obj = updatableObjectsController.getFirst();
        while (obj != null){
            tableCount.merge(obj.getType(), 1, Integer::sum); // увеличиваем счетчик определенных объектов потому что зачем проходить по массиву несколько раз?
            obj.update();
            obj = updatableObjectsController.getNext(obj);
        }
    }

    public int getCount(TypeTileObject type){
        return tableCount.get(type);
    }

    public void recalculateObjects(){
        for(TypeTileObject type : TypeTileObject.values()) tableCount.put(type, 0);
        UpdatableTileObject obj = updatableObjectsController.getFirst();
        while (obj != null){
            tableCount.merge(obj.getType(), 1, Integer::sum);
            obj = updatableObjectsController.getNext(obj);
        }
    }

    public int getCountAllObjects(){
        int count = 0;
        for(int countObj : tableCount.values()) count += countObj;
        return count;
    }

    public AbstractVisorStrategy getVisorStrategy() {
        return visorStrategy;
    }

    public void setVisorStrategy(AbstractVisorStrategy visorStrategy) {
        this.visorStrategy = visorStrategy;
    }

    public int getCountUpdate() {
        return countUpdate;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
}
