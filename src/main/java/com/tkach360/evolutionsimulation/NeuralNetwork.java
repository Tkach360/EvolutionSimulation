package com.tkach360.evolutionsimulation;

import com.tkach360.evolutionsimulation.neuralnetwork.PerceptronNetwork;

import java.util.ArrayList;

public class NeuralNetwork implements IBehavior {

    private PerceptronNetwork perceptronNetwork;

    public NeuralNetwork() {
        this.perceptronNetwork = new PerceptronNetwork(13, 15, 15, 23);
        this.perceptronNetwork.mutate(0, 0.5);
    }

    public NeuralNetwork(PerceptronNetwork perceptronNetwork) {
        this.perceptronNetwork = perceptronNetwork;
    }

    @Override
    public Decide decide(Bot bot) {
        double[] inputData = new double[13];
        ArrayList<Tile> tiles = bot.getTilesInVisibleArea();

        if(tiles.get(0).getAbstractTileObject() == null) inputData[0] = (double)bot.getTile().getSoilEnergy() / 10;
        else inputData[0] = 1.0;

        if(tiles.get(1).getAbstractTileObject() == null) inputData[1] = (double)bot.getTile().getSoilEnergy() / 10;
        else inputData[1] = 1.0;

        if(tiles.get(2).getAbstractTileObject() == null) inputData[2] = (double)bot.getTile().getSoilEnergy() / 10;
        else inputData[2] = 1.0;

        if(tiles.get(3).getAbstractTileObject() == null) inputData[2] = (double)bot.getTile().getSoilEnergy() / 10;
        else inputData[3] = 1.0;

        if(tiles.get(5).getAbstractTileObject() == null) inputData[2] = (double)bot.getTile().getSoilEnergy() / 10;
        else inputData[4] = 1.0;

        inputData[5] = (double)tiles.get(0).getLighting() / 10;
        inputData[6] = (double)tiles.get(1).getLighting() / 10;
        inputData[7] = (double)tiles.get(2).getLighting() / 10;
        inputData[8] = (double)tiles.get(3).getLighting() / 10;
        inputData[9] = (double)tiles.get(5).getLighting() / 10;

        inputData[10] = bot.getEnergy();
        inputData[11] = bot.getTile().getLighting();
        inputData[12] = bot.getTile().getSoilEnergy();

        this.perceptronNetwork.process(inputData);
        double[] outputs = this.perceptronNetwork.getOutputs();

        Decide decide;


        int numMax = 0;
        double max = outputs[0];
        for(int i=1; i < outputs.length; i++){
            if(outputs[i] > max) {
                max = outputs[i];
                numMax = i;
            }
        }
        //System.out.println(numMax);

        return switch (numMax) {
            case 0 -> Decide.MOVE_0;
            case 1 -> Decide.MOVE_1;
            case 2 -> Decide.MOVE_2;
            case 3 -> Decide.ROTATE_LEFT;
            case 4 -> Decide.ROTATE_DOWN;
            case 5 -> Decide.ROTATE_RIGHT;
            case 6 -> Decide.PHOTOSYNTHESIZE;
            case 7 -> Decide.CONSUME_SOIL;
            case 8 -> Decide.EAT_0;
            case 9 -> Decide.EAT_1;
            case 10 -> Decide.EAT_2;
            case 11 -> Decide.PRODUCE_0_DOWN;
            case 12 -> Decide.PRODUCE_0_LEFT;
            case 13 -> Decide.PRODUCE_0_RIGHT;
            case 14 -> Decide.PRODUCE_0_UP;
            case 15 -> Decide.PRODUCE_1_DOWN;
            case 16 -> Decide.PRODUCE_1_LEFT;
            case 17 -> Decide.PRODUCE_1_RIGHT;
            case 18 -> Decide.PRODUCE_1_UP;
            case 19 -> Decide.PRODUCE_2_DOWN;
            case 20 -> Decide.PRODUCE_2_LEFT;
            case 21 -> Decide.PRODUCE_2_RIGHT;
            case 22 -> Decide.PRODUCE_2_UP;
            default -> throw new IllegalStateException("Unexpected value: " + numMax);
        };

        /*if(outputs[0] < 0.33){
            if(outputs[2] < 0.33) decide = Decide.ROTATE_LEFT;
            else if(outputs[2] < 0.67) decide = Decide.ROTATE_DOWN;
            else decide = Decide.ROTATE_RIGHT;
        } else if (outputs[0] < 0.67){
            if(outputs[3] < 0.33){
                //System.out.println("PREDATION");
                if(outputs[4] < 0.33) decide = Decide.EAT_0;
                else if(outputs[4] < 0.67) decide = Decide.EAT_1;
                else decide = Decide.EAT_2;
            }
            else if(outputs[3] < 0.67){
                if(outputs[4] < 0.33) decide = Decide.MOVE_0;
                else if(outputs[4] < 0.67) decide = Decide.MOVE_1;
                else decide = Decide.MOVE_2;
            }
            else{
                if(outputs[4] < 0.33) decide = Decide.PRODUCE_0;
                else if(outputs[4] < 0.67) decide = Decide.PRODUCE_1;
                else decide = Decide.PRODUCE_2;
            }
        } else {
            if(outputs[1] < 0.5) {
                //System.out.println("PHOTOSYNTEZIS");
                decide = Decide.PHOTOSYNTHESIZE;
            }
            else {
                //System.out.println("SOIL");
                decide = Decide.CONSUME_SOIL;
            }
        }*/

    }

    @Override
    public IBehavior copyWitchChange(double rate, double strength) {

        PerceptronNetwork p = this.perceptronNetwork.copy();
        p.mutate(rate, strength);
        return new NeuralNetwork(p);
    }

    public PerceptronNetwork getPerceptronNetwork() {
        return perceptronNetwork;
    }
}
