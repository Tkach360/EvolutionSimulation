package com.tkach360.evolutionsimulation;

public class BotNode{
    private Bot bot;
    private int next;
    private int prev;
    private final int index;
    private final IBotsController botsController;

    public BotNode(IBotsController botsController, int index) {
        this.botsController = botsController;
        this.bot = null;
        this.next = 0;
        this.prev = 0;
        this.index = index;
    }

    public void registerNewBot(Bot bot){
        botsController.addBot(bot, this.index);
    }

    public void unRegisterBot(){
        botsController.delBot(this.bot);
        this.bot = null;
    }

    public int getIndex(){
        return this.index;
    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }
}
