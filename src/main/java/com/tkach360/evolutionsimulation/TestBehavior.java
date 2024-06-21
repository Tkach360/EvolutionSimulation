package com.tkach360.evolutionsimulation;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class TestBehavior implements IBehavior{

    @Override
    public Decide decide(Bot bot) {

        Random random = new Random();

        return Decide.values()[random.nextInt(14)];
    }

    @Override
    public TestBehavior copyWitchChange(double rate, double strength){
        return new TestBehavior();
    }
}
