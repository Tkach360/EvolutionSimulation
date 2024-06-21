package com.tkach360.evolutionsimulation.neuralnetwork;

public class HiddenLayer {

    private double[][] weights;
    private double[] biases;

    public HiddenLayer(int currentLayerSize, int previousLayerSize) {
        weights = new double[currentLayerSize][previousLayerSize];
        biases = new double[currentLayerSize];
        initRandom();
    }

    private void initRandom() {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                weights[i][j] = 2 * Math.random() - 1; // [-1 .. 1)
                //weights[i][j] = Math.random(); // [0, 1)
            }
        }

        for (int i = 0; i < biases.length; i++) {
            biases[i] = 2 * Math.random() - 1; // [-1 .. 1)
            //biases[i] = Math.random(); // [0, 1)
        }
    }

    public double[] multiplyAddBias(double[] activation) {
        if (activation.length != weights[0].length) {
            throw new IllegalArgumentException("Invalid activation length " + activation.length);
        }

        double[] result = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            double sum = 0;
            for (int j = 0; j < weights[0].length; j++) {
                sum += activation[j] * weights[i][j];
            }
            sum += biases[i];
            result[i] = sum;
        }

        return result;
    }

    public double[][] getWeights() {
        return weights;
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
    }

    public double[] getBiases() {
        return biases;
    }

    public void setBiases(double[] biases) {
        this.biases = biases;
    }
}
