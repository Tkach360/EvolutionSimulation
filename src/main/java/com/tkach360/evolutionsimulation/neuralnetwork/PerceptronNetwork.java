package com.tkach360.evolutionsimulation.neuralnetwork;

import java.io.*;
import java.util.Random;

// See https://github.com/AppliedCodingClub/YouTubeVideos
public class PerceptronNetwork implements Serializable {

    private String filename;

    private double[][] activations;
    private HiddenLayer[] hiddenLayers;

    private PerceptronNetwork(){}

    public PerceptronNetwork(int... layers){
        activations = new double[layers.length][];
        hiddenLayers = new HiddenLayer[layers.length - 1];

        activations[0] = new double[layers[0]];

        for (int i = 1; i < layers.length; i++) {
            activations[i] = new double[layers[i]];
            hiddenLayers[i - 1] = new HiddenLayer(layers[i], layers[i - 1]);
        }
    }

    public void process(double[] inputs) {
        if (inputs.length != activations[0].length) {
            throw new IllegalArgumentException("Invalid number of inputs " + inputs.length);
        }

        System.arraycopy(inputs, 0, activations[0], 0, inputs.length);

        for (int i = 0; i < activations.length - 1; i++) {
            activations[i + 1] = hiddenLayers[i].multiplyAddBias(activations[i]);
            relu(activations[i + 1]);
            sigmoid(activations[i + 1]);//
        }
    }

    private void relu(double[] activation) {
        for (int i = 0; i < activation.length; i++) {
            activation[i] = Math.max(0, activation[i]);
        }
    }

    private void sigmoid(double[] activation) {
        for (int i = 0; i < activation.length; i++) {
            activation[i] = 1.0 / (1 + Math.exp(-activation[i]));
        }
    }

    public void mutate(double rate, double strength) {
        Random random = new Random();

        for (HiddenLayer hiddenLayer : hiddenLayers) {
            double[][] weights = hiddenLayer.getWeights();
            for (int i = 0; i < weights.length; i++) {
                for (int j = 0; j < weights[0].length; j++) {
                    if (Math.random() < rate) {
                        weights[i][j] += random.nextGaussian() * strength;
                    }
                }
            }

            double[] biases = hiddenLayer.getBiases();
            for (int i = 0; i < biases.length; i++) {
                if (Math.random() < rate) {
                    biases[i] += random.nextGaussian() * strength;
                }
            }
        }
    }

/*    public int getActivatedOutput() {
        double[] outputs = getOutputs();
        int result = 0;
        double maxOutput = outputs[0];

        for (int i = 1; i < outputs.length; i++) {
            if (outputs[i] > maxOutput) {
                maxOutput = outputs[i];
                result = i;
            }
        }

        return maxOutput == 0 ? -1 : result;
    }*/

    public double[] getOutputs() {
        return activations[activations.length - 1];
    }

    public void setActivations(double[][] activations) {
        this.activations = activations;
    }

    public void setHiddenLayers(HiddenLayer[] hiddenLayers) {
        this.hiddenLayers = hiddenLayers;
    }

    public PerceptronNetwork copy(){

        PerceptronNetwork p = new PerceptronNetwork();
        p.setActivations(activations.clone());
        p.setHiddenLayers(hiddenLayers.clone());

        return p;
    }

    /*public NeuralNetworkBehavior(String filename) {
        this(XmlSerializer.fromXml(new File(filename), PerceptronNetwork.class));
        this.filename = filename;
    }*/

    /*public void saveInFile(String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    public static PerceptronNetwork load(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        PerceptronNetwork obj = (PerceptronNetwork) in.readObject();
        in.close();
        fileIn.close();
        return obj;
    }

    public void clearActivations() {
        for (int i = 0; i < activations.length; i++) {
            for (int j = 0; j < activations[i].length; j++) {
                activations[i][j] = 0;
            }
        }
    }*/

}
