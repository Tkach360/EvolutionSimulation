package com.tkach360.evolutionsimulation;

import java.util.ArrayList;
import java.util.Random;

public class TestBehavior extends AbstractBehavior{

    public TestBehavior(Bot bot) {
        super(bot);
    }

    @Override
    public void doSomething() {

        Random random = new Random();

        ArrayList<Tile> tilesNear = bot.getVisibleArea().getTilesNear(bot.getTile());
        ArrayList<Tile> cleanTiles = new ArrayList<>();
        ArrayList<Tile> notCleanTiles = new ArrayList<>();

        for(Tile tile : tilesNear) {
            if(tile.getAbstractTileObject() == null) cleanTiles.add(tile);
            else notCleanTiles.add(tile);
        }

        if (!cleanTiles.isEmpty()){
            if(this.bot.getEnergy() > 70) {
                this.bot.produceNewBot(cleanTiles.get(random.nextInt(cleanTiles.size())));
                switch (random.nextInt(2)){
                    case 0:
                        this.bot.moveForward();
                        break;
                    case 1:
                        this.bot.setVisibleArea(new VisibleArea(random));
                        break;
                }
            }
            else{
                switch (random.nextInt(3)){
                    case 0:
                        this.bot.photosynthesize();
                        break;
                    case 1:
                        this.bot.consumeSoil();
                        break;
                    case 2:
                        if(!notCleanTiles.isEmpty()){
                            Tile tile = notCleanTiles.get(random.nextInt(notCleanTiles.size()));
                            this.bot.eatBot((Bot)(tile.getAbstractTileObject()));
                        }
                        else {
                            switch (random.nextInt(2)){
                                case 0:
                                    this.bot.moveForward();
                                    break;
                                case 1:
                                    this.bot.setVisibleArea(new VisibleArea(random));
                                    break;
                            }
                        }
                        break;
                }
            }
        }
        else{
            Tile tile = notCleanTiles.get(random.nextInt(notCleanTiles.size()));
            this.bot.eatBot((Bot)(tile.getAbstractTileObject()));
        }
    }
}
