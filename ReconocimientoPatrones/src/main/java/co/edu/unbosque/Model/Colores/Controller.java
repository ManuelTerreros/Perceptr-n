package co.edu.unbosque.Model.Colores;

public class Controller {
    private final SimpleNeuronColor neuron;

    public Controller() {
        neuron = new SimpleNeuronColor();
        double[] targets = new double[]{1, 0, 1, 0, 0, 1, 1, 1, 0};
        double learningRate = 0.001;
        co.edu.unbosque.Model.Colores.Color white = new co.edu.unbosque.Model.Colores.Color(255, 255, 255);
        co.edu.unbosque.Model.Colores.Color black = new co.edu.unbosque.Model.Colores.Color(0, 0, 0);
        co.edu.unbosque.Model.Colores.Color lightBlue = new co.edu.unbosque.Model.Colores.Color(0, 255, 255);
        co.edu.unbosque.Model.Colores.Color darkBlue = new co.edu.unbosque.Model.Colores.Color(0, 0, 255);
        co.edu.unbosque.Model.Colores.Color red = new co.edu.unbosque.Model.Colores.Color(255, 0, 0);
        co.edu.unbosque.Model.Colores.Color green = new co.edu.unbosque.Model.Colores.Color(0, 255, 0);
        co.edu.unbosque.Model.Colores.Color lime = new co.edu.unbosque.Model.Colores.Color(0, 255, 100);
        co.edu.unbosque.Model.Colores.Color yellow = new co.edu.unbosque.Model.Colores.Color(255, 255, 102);
        co.edu.unbosque.Model.Colores.Color purple = new co.edu.unbosque.Model.Colores.Color(102, 0, 255);
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
        co.edu.unbosque.Model.Colores.Color pink = new co.edu.unbosque.Model.Colores.Color(255, 204, 255);
        double[] input = {pink.red(), pink.green(), pink.blue()};
        System.out.println(neuron.predictColor(input));
        co.edu.unbosque.Model.Colores.Color darkGreen = new co.edu.unbosque.Model.Colores.Color(0, 51, 0);
        double[] input2 = {darkGreen.red(), darkGreen.green(), darkGreen.blue()};
        System.out.println(neuron.predictColor(input2));
        co.edu.unbosque.Model.Colores.Color otherBlue = new co.edu.unbosque.Model.Colores.Color(0, 0, 102);
        double[] input3 = {otherBlue.red(), otherBlue.green(), otherBlue.blue()};
        System.out.println(neuron.predictColor(input3));
        System.out.println("------------------------");
    }


    public void train(double[][] inputs, double[] targets, double learningRate) {
        neuron.train(inputs, targets, learningRate);
    }

    /**
     * Converts a given Color object to an array of doubles representing the RGB values.
     *
     * @param color the Color object to convert
     * @return an array of doubles representing the RGB values
     */
    public double[] addColor(Color color) {
        return new double[]{color.red(), color.green(), color.blue()};
    }
}
