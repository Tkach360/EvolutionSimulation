package com.tkach360.evolutionsimulation;

public class BotsController {

    private static BotsController instance;

    private BotNode[] botsList;

    private BotsController(int maxCountBots) {
        maxCountBots++;
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
                int next = botsList[prev].getNext();

                bot.setBotNode(botsList[i]);
                botsList[i].setBot(bot);
                botsList[i].setPrev(prev);
                botsList[i].setNext(next);

                botsList[prev].setNext(i);
                botsList[next].setPrev(i);

                return;
            }
        }
    }

    public void delBot(Bot bot){
        int prevInd = bot.getBotNode().getPrev();
        int nextInd = bot.getBotNode().getNext();

        botsList[nextInd].setPrev(prevInd);
        botsList[prevInd].setNext(nextInd);
        bot.getBotNode().setBot(null);
    }

    public void update(){
        int index = getNextBotNodeIndex(0);
        while(index != 0){
            botsList[index].getBot().doSomething();
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
