package com.tkach360.evolutionsimulation;

import java.util.ArrayList;
import java.util.Random;

public class UpdateController {

    private int countUpdate;
    private int countBots;
    private long lastUpdateTime;
    private AbstractVisorStrategy visorStrategy;

    public UpdateController(AbstractVisorStrategy visorStrategy) {
        this.visorStrategy = visorStrategy;
        this.lastUpdateTime = 0;
        this.countUpdate = 0;
        this.countBots = 0;
    }

    // TODO: тестовый
    private void updateTileObjects(){
        BotsController.getInstance().update();
    }

    public void updateValues(){
        countUpdate++;
        int newCountBots = 0;
        int index = BotsController.getInstance().getNextBotNodeIndex(0);
        while(index != 0){
            newCountBots++;
            index = BotsController.getInstance().getNextBotNodeIndex(index);
        }
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

    public void updateVisor(AbstractVisorStrategy newVisorStrategy){
        setVisorStrategy(newVisorStrategy);
        this.visorStrategy.drawAll();
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
