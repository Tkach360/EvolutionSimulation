package com.tkach360.evolutionsimulation;

public class BotsController {

    private static BotsController instance;
    private class BotNode{
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

    private BotNode[] botsList;

    private BotsController(int maxCountBots) {
        this.botsList = new BotNode[maxCountBots];
        for (int i = 0; i < maxCountBots; i++) this.botsList[i] = new BotNode();
    }

    public static BotsController getInstance(int maxCountBots){
        if(instance == null) instance = new BotsController(maxCountBots);
        return instance;
    }

    public static BotsController getInstance(){
        return instance;
    }

    public void addBot(Bot bot, int prev){
        for(int i = 1; i < botsList.length; i++){
            if(botsList[i].getBot() == null) {

                botsList[i].setBot(bot);
                botsList[i].setPrev(prev);
                botsList[i].setNext(botsList[prev].getNext());

                botsList[prev].setNext(i);
                return;
            }
        }
    }

    public void update(){
        int index = getNextBotNodeIndex(0);
        while(index != 0){
            botsList[index].getBot().doSomething(index);
            index = getNextBotNodeIndex(index);
        }
    }

    public int getNextBotNodeIndex(int index){
        return botsList[index].getNext();
    }

    public Bot getBot(int index){
        return botsList[index].getBot();
    }
}
