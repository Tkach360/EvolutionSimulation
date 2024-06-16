package com.tkach360.evolutionsimulation;

public class UpdateController {

    private int countUpdate;
    private int countBots;
    private long lastUpdateTime;
    private AbstractVisorStrategy visorStrategy;
    private final IBotsController botsController;

    public UpdateController(AbstractVisorStrategy visorStrategy, IBotsController botsController) {
        this.botsController = botsController;
        this.visorStrategy = visorStrategy;
        this.lastUpdateTime = 0;
        this.countUpdate = 0;
        this.countBots = 0;
    }

    // TODO: тестовый, пока нет других TileObjects
    private void updateTileObjects(){
        updateBots();
    }

    public void updateValues(){
        this.countBots = botsController.getCountBots();
    }

    public void updateAll(){
        countUpdate++;
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

    private void updateBots(){
        Bot bot = botsController.getFirst();
        while (bot != null){
            bot.update();
            bot = botsController.getNextBot(bot);
        }
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
