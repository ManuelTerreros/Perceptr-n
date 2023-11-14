package co.edu.unbosque.Model.Colores;

public class Controller {
    private final SimpleNeuron neuron;

    public Controller() {
        neuron = new SimpleNeuron();
        double[] targets = new double[]{1, 0, 1, 0, 0, 1, 1, 1, 0};
        double learningRate = 0.001;
        Color white = new Color(255, 255, 255);
        Color black = new Color(0, 0, 0);
        Color lightBlue = new Color(0, 255, 255);
        Color darkBlue = new Color(0, 0, 255);
        Color red = new Color(255, 0, 0);
        Color green = new Color(0, 255, 0);
        Color lime = new Color(0, 255, 100);
        Color yellow = new Color(255, 255, 102);
        Color purple = new Color(102, 0, 255);
        double[][] inputs = new double[9][3];
        inputs[0] = addColor(white);
        inputs[1] = addColor(black);
        inputs[2] = addColor(lightBlue);
        inputs[3] = addColor(darkBlue);
        inputs[4] = addColor(red);
        inputs[5] = addColor(green);
        inputs[6] = addColor(lime);
        inputs[7] = addColor(yellow);
        inputs[8] = addColor(purple);
        neuron.train(inputs, targets, learningRate);
        Color pink = new Color(255, 204, 255);
        double[] input = {pink.red(), pink.green(), pink.blue()};
        System.out.println(neuron.predict(input));
        Color darkGreen = new Color(0, 51, 0);
        double[] input2 = {darkGreen.red(), darkGreen.green(), darkGreen.blue()};
        System.out.println(neuron.predict(input2));
        Color otherBlue = new Color(0, 0, 102);
        double[] input3 = {otherBlue.red(), otherBlue.green(), otherBlue.blue()};
        System.out.println(neuron.predict(input3));
        System.out.println("------------------------");
    }



    public void train(double[][] inputs, double[] targets, double learningRate) {
        neuron.train(inputs, targets, learningRate);
    }
    /**
     * Converts a given Color object to an array of doubles representing the RGB values.
     *
     * @param  color  the Color object to convert
     * @return        an array of doubles representing the RGB values
     */
    public double[] addColor(Color color) {
        return new double[]{color.red(), color.green(), color.blue()};
    }
}
