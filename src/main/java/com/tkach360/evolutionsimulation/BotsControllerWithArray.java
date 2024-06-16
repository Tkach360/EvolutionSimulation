package com.tkach360.evolutionsimulation;

public class BotsControllerWithArray implements IBotsController {

    private final BotNode[] botsList;

    public BotsControllerWithArray(int maxCountBots) {
        this.botsList = new BotNode[maxCountBots + 1];
        for (int i = 0; i < maxCountBots + 1; i++) this.botsList[i] = new BotNode(this, i);
    }

    @Override
    public void addBot(Bot bot, int parentIndex){
        for(int i = 1; i < botsList.length; i++){
            if(botsList[i].getBot() == null) {
                int next = botsList[parentIndex].getNext();

                bot.setBotNode(botsList[i]);
                botsList[i].setBot(bot);
                botsList[i].setPrev(parentIndex);
                botsList[i].setNext(next);

                botsList[parentIndex].setNext(i);
                botsList[next].setPrev(i);

                return;
            }
        }
    }

    @Override
    public void delBot(Bot bot){
        int prevInd = bot.getBotNode().getPrev();
        int nextInd = bot.getBotNode().getNext();

        botsList[nextInd].setPrev(prevInd);
        botsList[prevInd].setNext(nextInd);
    }

    @Override
    public Bot getNextBot(Bot bot) {
        Bot nextBot;
        int next = bot.getBotNode().getNext();

        if(next == 0) nextBot = null;
        else nextBot = botsList[next].getBot();

        return nextBot;
    }

    @Override
    public Bot getFirst(){
        return botsList[botsList[0].getNext()].getBot();
    }

    @Override
    public int getCountBots() {
        int count = 0;
        Bot bot = getFirst();
        while (bot != null){
            count++;
            bot = getNextBot(bot);
        }
        return count;
    }
}
