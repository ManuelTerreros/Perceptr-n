package co.edu.unbosque.Model.Numeros;

import java.util.Arrays;
import java.util.Random;

public class SimpleNumberNeuronV2 {
    private double[] weights;

    public SimpleNumberNeuronV2(int weightCount) {
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
                double prediction = predict(inputs[i]);
                if (prediction <= ((double) 5 / 7)) {
                    prediction = 5;
                } else if (prediction > ((double) 5 / 7) && prediction <= ((double) 6 / 7)) {
                    prediction = 6;
                } else if (prediction > ((double) 6 / 7) && prediction <= 1) {
                    prediction = 7;
                } else if (prediction > 1) {
                    prediction = 8;
                }
                if ((targets[i] == prediction)) {
                    System.out.println("Target: " + targets[i] + " Prediction: " + prediction);
                } else {
                    System.out.println("Calculation error target: " + targets[i] + " prediction: " + prediction);
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

    public double predict(double[] input) {
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

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;

    }


}
