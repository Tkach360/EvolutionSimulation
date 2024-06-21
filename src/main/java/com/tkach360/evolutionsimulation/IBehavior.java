package com.tkach360.evolutionsimulation;

import java.util.Random;

public interface IBehavior {
    Decide decide(Bot bot);
    IBehavior copyWitchChange(double rate, double strength);
}
