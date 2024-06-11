package com.tkach360.evolutionsimulation;

import java.util.ArrayList;
import java.util.Random;

public class UpdateController {

    private int countUpdate;
    private int countBots;
    private long lastUpdateTime;
    private ArrayList<AbstractTileObject> abstractTileObjects;
    private AbstractVisorStrategy visorStrategy;

    public UpdateController(ArrayList<AbstractTileObject> abstractTileObjects, AbstractVisorStrategy visorStrategy) {
        this.abstractTileObjects = abstractTileObjects;
        this.visorStrategy = visorStrategy;
        this.lastUpdateTime = 0;
        this.countUpdate = 0;
        this.countBots = 0;
    }

    // TODO: тестовый
    private void updateTileObjects(){

        Random random = new Random(1);
        for(AbstractTileObject bot : abstractTileObjects){
            if(bot instanceof Bot){
                Bot b = (Bot) bot;
                if (b.getVisibleArea().getTileInVisibleArea(1, b.getTile()).getAbstractTileObject() != null) b.getVisibleArea().setDirection(random);
                b.moveForward();
            }
        }
    }

    private void updateValues(){
        countUpdate++;
        int newCountBots = 0;
        for(AbstractTileObject bot : abstractTileObjects) if(bot instanceof Bot) newCountBots++;
        countBots = newCountBots;
    }

    public void updateAll(){
        long startUpdateTime = System.nanoTime();

        updateTileObjects();

        long endUpdateTime = System.nanoTime();
        lastUpdateTime = endUpdateTime - startUpdateTime;

        updateValues();
        visorStrategy.drawAll();
    }

    public ArrayList<AbstractTileObject> getAbstractTileObjects() {
        return abstractTileObjects;
    }

    public void setAbstractTileObjects(ArrayList<AbstractTileObject> abstractTileObjects) {
        this.abstractTileObjects = abstractTileObjects;
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

    public int getCountBots() {
        return countBots;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
}
