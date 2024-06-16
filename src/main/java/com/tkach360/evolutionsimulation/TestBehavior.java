package com.tkach360.evolutionsimulation;

import java.util.ArrayList;
import java.util.Random;

public class TestBehavior extends AbstractBehavior{

    @Override
    public void doSomething(Bot bot) {

        Random random = new Random();

        ArrayList<Tile> tilesNear = bot.getTilesNear(bot.getTile());
        ArrayList<Tile> cleanTiles = new ArrayList<>();
        ArrayList<Tile> notCleanTiles = new ArrayList<>();

        for(Tile tile : tilesNear) {
            if(tile.getAbstractTileObject() == null) cleanTiles.add(tile);
            else notCleanTiles.add(tile);
        }

        if (!cleanTiles.isEmpty()){
            if(bot.getEnergy() > Bot.minEnergyReproduction + 20) {
                bot.produceNewBot(cleanTiles.get(random.nextInt(cleanTiles.size())));
                switch (random.nextInt(2)){
                    case 0:
                        bot.moveForward();
                        break;
                    case 1:
                        bot.setDirection(Direction.getRandom(random));
                        break;
                }
            }
            else{
                switch (random.nextInt(3)){
                    case 0:
                        bot.photosynthesize();
                        break;
                    case 1:
                        bot.consumeSoil();
                        break;
                    case 2:
                        if(!notCleanTiles.isEmpty()){
                            Tile tile = notCleanTiles.get(random.nextInt(notCleanTiles.size()));
                            bot.eatBot((Bot)(tile.getAbstractTileObject()));
                        }
                        else {
                            switch (random.nextInt(2)){
                                case 0:
                                    bot.moveForward();
                                    break;
                                case 1:
                                    bot.setDirection(Direction.getRandom(random));
                                    break;
                            }
                        }
                        break;
                }
            }
        }
        else{
            Tile tile = notCleanTiles.get(random.nextInt(notCleanTiles.size()));
            bot.eatBot((Bot)(tile.getAbstractTileObject()));
        }
    }
}
