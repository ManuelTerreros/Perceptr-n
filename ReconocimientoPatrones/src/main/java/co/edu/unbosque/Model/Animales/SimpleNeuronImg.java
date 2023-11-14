package co.edu.unbosque.Model.Animales;

import java.util.Arrays;
import java.util.Random;

public class SimpleNeuronImg {
    private double[] weights;

    public SimpleNeuronImg(int weightCount) {
        Random random = new Random();
        weights = new double[weightCount];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextDouble(0, 10);
        }

    }

    /**
     * Trains the model using the given inputs, targets, and learning rate.
     *
     * @param inputs       the input data for training
     * @param targets      the target values for training
     * @param learningRate the learning rate for adjusting weights
     * @return the number of iterations
     */
    public int train(double[][] inputs, double[] targets, double learningRate) {
        boolean ok = false;
        int iterations = 0;
        while (!ok) {
            ok = true;
            for (int i = 0; i < inputs.length; i++) {
                double prediction = predictImg(inputs[i]);
                if (prediction <= 0) {
                    prediction = 0;
                } else if (prediction > 0) {
                    prediction = 1;
                }
                if (targets[i] == prediction) {
                    System.out.println("Target: " + targets[i] + " Prediction: " + prediction);
                    // System.out.println("Weights: " + Arrays.toString(weights));
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
        return iterations;
    }

    /**
     * Predicts the output based on the given input array.
     *
     * @param input the input array used for prediction
     * @return the predicted output
     */
    public double predictImg(double[] input) {
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
