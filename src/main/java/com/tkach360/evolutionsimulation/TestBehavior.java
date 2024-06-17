package com.tkach360.evolutionsimulation;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class TestBehavior implements IBehavior{

    @Override
    public int decide(Bot bot) {

        Random random = new Random();

        ArrayList<Pair<Integer, Tile>> tilesNear = bot.getTilesInVisibleAreaWitchIndex();
        ArrayList<Pair<Integer, Tile>> cleanTiles = new ArrayList<>();
        ArrayList<Pair<Integer, Tile>> notCleanTiles = new ArrayList<>();

        for(Pair<Integer, Tile> tile : tilesNear) {
            if(tile.getValue().getAbstractTileObject() == null) cleanTiles.add(tile);
            else notCleanTiles.add(tile);
        }

        if (!cleanTiles.isEmpty()){
            if(bot.getEnergy() > Bot.minEnergyReproduction + 20) {
                bot.produceNewBot(cleanTiles.get(random.nextInt(cleanTiles.size())).getValue());
                switch (random.nextInt(2)){
                    case 0:
                        return 1;
                    case 1:
                        return random.nextInt(3, 6);
                }
            }
            else{
                switch (random.nextInt(3)){
                    case 0:
                        return 6;
                    case 1:
                        return 7;
                    case 2:
                        if(!notCleanTiles.isEmpty()){
                            int i = notCleanTiles.get(random.nextInt(notCleanTiles.size())).getKey();
                            return i + 8;
                        }
                        else {
                            switch (random.nextInt(2)){
                                case 0:
                                    return 1;
                                case 1:
                                    return random.nextInt(3, 6);
                            }
                        }
                        break;
                }
            }
        }
        else{
            int i = notCleanTiles.get(random.nextInt(notCleanTiles.size())).getKey();
            return i + 8;
        }
        return 3;
    }

    @Override
    public TestBehavior copyWitchChange(int mutationSpread, Random random){
        return new TestBehavior();
    }
}
