package com.tkach360.evolutionsimulation;

public interface IBotsController {
    void addBot(Bot bot, int parentIndex);
    void delBot(Bot bot);
    int getCountBots();
    Bot getNextBot(Bot bot);
    Bot getFirst();
}
