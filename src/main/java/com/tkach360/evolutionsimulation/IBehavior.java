package com.tkach360.evolutionsimulation;

import java.util.Random;

public interface IBehavior {
    int decide(Bot bot);
    IBehavior copyWitchChange(int mutationSpread, Random random);
}
