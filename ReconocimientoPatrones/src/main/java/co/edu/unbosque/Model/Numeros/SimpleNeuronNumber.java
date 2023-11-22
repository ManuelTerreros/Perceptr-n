package co.edu.unbosque.Model.Numeros;

import java.util.Arrays;
import java.util.Random;

public class SimpleNeuronNumber {
    private double[] weights;

    public SimpleNeuronNumber(int weightCount) {
        Random random = new Random();
        weights = new double[weightCount];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextDouble(0, 10);
        }

    }

    public void train(double[][] inputs, double[] targets, double learningRate) {
        boolean ok = false;
        int iterations = 0;
        while (!ok) {
            ok = true;
            for (int i = 0; i < inputs.length; i++) {
                double prediction = predictNumber(inputs[i]);
                if (prediction <= 0) {
                    prediction = 0;
                } else if (prediction > 0 && prediction <= ((double) 1 / 3)) {
                    prediction = 1;
                } else if (prediction > ((double) 1 / 3) && prediction <= ((double) 2 / 3)) {
                    prediction = 2;
                } else if (prediction > ((double) 2 / 3) && prediction <= 1) {
                    prediction = 3;
                } else if (prediction > 1) {
                    prediction = 4;
                }
                if (targets[i] == prediction) {
                    System.out.println("Target: " + targets[i] + " Prediction: " + prediction);
                } else {
                    System.out.println("Calculation error");
                    double error = targets[i] - prediction;
                    for (int j = 0; j < weights.length; j++) {
                        if (j == weights.length - 1) {
                            weights[j] += learningRate * error;
                        } else {
                            weights[j] += learningRate * error * inputs[i][j];
                        }
                    }
                    ok = false;
                }
            }
            iterations++;
        }
        System.out.println("Final weights: " + Arrays.toString(weights));
        System.out.println("Iterations: " + iterations);

    }


    public double predictNumber(double[] input) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            if (i == weights.length - 1) {
                sum += weights[i];
            } else {
                sum += weights[i] * input[i];
            }
        }
        return sum;
    }


    public void setWeights(double[] weights) {
        this.weights = weights;

    }

    public double[] getWeights() {
        return weights;
    }
}
