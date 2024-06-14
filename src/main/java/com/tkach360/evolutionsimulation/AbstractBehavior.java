package com.tkach360.evolutionsimulation;

public abstract class AbstractBehavior {
    protected Bot bot;

    public AbstractBehavior(Bot bot) {
        this.bot = bot;
    }

    public abstract void doSomething();
}
