package com.tkach360.evolutionsimulation;

public class BotNode{
    private Bot bot;
    private int next;
    private int prev;

    public BotNode() {
        this.bot = null;
        this.next = 0;
        this.prev = 0;
    }

    public BotNode(Bot bot, int next, int prev) {
        this.bot = bot;
        this.next = next;
        this.prev = prev;
    }

    public int getIndex(){
        return BotsController.getInstance().getNextBotNodeIndex(prev);
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
