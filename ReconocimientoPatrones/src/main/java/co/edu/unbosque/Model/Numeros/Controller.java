package co.edu.unbosque.Model.Numeros;


public class Controller {
    private final SimpleNeuron neuron;

    public Controller() {
        double[] objectic = {0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3,0};
        neuron = new SimpleNeuron(26);
        
        Number number0 = new Number();
        number0.setNumber(0, 0);
        number0.setNumber(0, 1);
        number0.setNumber(0, 2);
        number0.setNumber(0, 3);
        number0.setNumber(0, 4);
        number0.setNumber(1, 0);
        number0.setNumber(1, 4);
        number0.setNumber(2, 0);
        number0.setNumber(2, 4);
        number0.setNumber(3, 0);
        number0.setNumber(3, 4);
        number0.setNumber(4, 0);
        number0.setNumber(4, 1);
        number0.setNumber(4, 2);
        number0.setNumber(4, 3);
        number0.setNumber(4, 4);

        Number number1 = new Number();
        number1.setNumber(0, 1);
        number1.setNumber(0, 2);
        number1.setNumber(0, 3);
        number1.setNumber(1, 0);
        number1.setNumber(1, 4);
        number1.setNumber(2, 0);
        number1.setNumber(2, 4);
        number1.setNumber(3, 0);
        number1.setNumber(3, 4);
        number1.setNumber(4, 1);
        number1.setNumber(4, 2);
        number1.setNumber(4, 3);

        Number number2 = new Number();
        number2.setNumber(0, 2);
        number2.setNumber(1, 2);
        number2.setNumber(2, 2);
        number2.setNumber(3, 2);
        number2.setNumber(4, 0);
        number2.setNumber(4, 0);
        number2.setNumber(4, 1);
        number2.setNumber(4, 2);
        number2.setNumber(4, 3);
        number2.setNumber(4, 4);

        Number number3 = new Number();
        number3.setNumber(0, 0);
        number3.setNumber(0, 1);
        number3.setNumber(0, 2);
        number3.setNumber(1, 2);
        number3.setNumber(2, 2);
        number3.setNumber(3, 2);
        number3.setNumber(4, 0);
        number3.setNumber(4, 1);
        number3.setNumber(4, 2);
        number3.setNumber(4, 3);
        number3.setNumber(4, 4);

        Number number4 = new Number();
        number4.setNumber(0, 0);
        number4.setNumber(0, 1);
        number4.setNumber(0, 2);
        number4.setNumber(1, 2);
        number4.setNumber(2, 2);
        number4.setNumber(3, 2);
        number4.setNumber(4, 2);

        Number number5 = new Number();
        number5.setNumber(0, 2);
        number5.setNumber(1, 2);
        number5.setNumber(2, 2);
        number5.setNumber(3, 2);
        number5.setNumber(4, 2);

        Number number6 = new Number();
        number6.setNumber(0, 1);
        number6.setNumber(0, 2);
        number6.setNumber(0, 3);
        number6.setNumber(1, 0);
        number6.setNumber(1, 4);
        number6.setNumber(2, 4);
        number6.setNumber(3, 3);
        number6.setNumber(4, 0);
        number6.setNumber(4, 1);
        number6.setNumber(4, 2);
        number6.setNumber(4, 3);
        number6.setNumber(4, 4);

        Number number7 = new Number();
        number7.setNumber(0, 1);
        number7.setNumber(0, 2);
        number7.setNumber(0, 3);
        number7.setNumber(1, 0);
        number7.setNumber(1, 4);
        number7.setNumber(2, 3);
        number7.setNumber(3, 2);
        number7.setNumber(4, 0);
        number7.setNumber(4, 1);
        number7.setNumber(4, 2);
        number7.setNumber(4, 3);
        number7.setNumber(4, 4);

        Number number8 = new Number();
        number8.setNumber(0, 1);
        number8.setNumber(0, 2);
        number8.setNumber(1, 0);
        number8.setNumber(1, 3);
        number8.setNumber(2, 2);
        number8.setNumber(3, 1);
        number8.setNumber(4, 0);
        number8.setNumber(4, 1);
        number8.setNumber(4, 2);
        number8.setNumber(4, 3);
        number8.setNumber(4, 4);

        Number number9 = new Number();
        number9.setNumber(0, 0);
        number9.setNumber(0, 1);
        number9.setNumber(0, 2);
        number9.setNumber(0, 3);
        number9.setNumber(0, 4);
        number9.setNumber(1,4);
        number9.setNumber(2, 2);
        number9.setNumber(2, 3);
        number9.setNumber(2, 4);
        number9.setNumber(3, 4);
        number9.setNumber(4, 0);
        number9.setNumber(4, 1);
        number9.setNumber(4, 2);
        number9.setNumber(4, 3);
        number9.setNumber(4, 4);

        Number number10 = new Number();
        number10.setNumber(0, 1);
        number10.setNumber(0, 2);
        number10.setNumber(0, 3);
        number10.setNumber(1,0);
        number10.setNumber(1,4);
        number10.setNumber(2, 2);
        number10.setNumber(2, 3);
        number10.setNumber(2, 4);
        number10.setNumber(3, 0);
        number10.setNumber(3, 4);
        number10.setNumber(4, 1);
        number10.setNumber(4, 2);
        number10.setNumber(4, 3);

        Number number11 = new Number();
        number11.setNumber(0, 1);
        number11.setNumber(0, 2);
        number11.setNumber(0, 3);
        number11.setNumber(1,0);
        number11.setNumber(1,4);
        number11.setNumber(2, 3);
        number11.setNumber(2, 4);
        number11.setNumber(3, 0);
        number11.setNumber(3, 4);
        number11.setNumber(4, 1);
        number11.setNumber(4, 2);
        number11.setNumber(4, 3);

        Number number13 = new Number();
        number13.setNumber(0, 0);
        number13.setNumber(0, 1);
        number13.setNumber(0, 2);
        number13.setNumber(0, 3);
        number13.setNumber(0, 4);
        number13.setNumber(1, 0);
        number13.setNumber(1, 4);
        number13.setNumber(2, 0);
        number13.setNumber(2, 4);
        number13.setNumber(3, 0);
        number13.setNumber(3, 4);
        number13.setNumber(4, 0);
        number13.setNumber(4, 1);
        number13.setNumber(4, 2);
        number13.setNumber(4, 3);
        number13.setNumber(4, 4);

        double[][] inputs = new double[13][25];
        inputs[0] = number0.getNumber();
        inputs[1] = number1.getNumber();
        inputs[2] = number2.getNumber();
        inputs[3] = number3.getNumber();
        inputs[4] = number4.getNumber();
        inputs[5] = number5.getNumber();
        inputs[6] = number6.getNumber();
        inputs[7] = number7.getNumber();
        inputs[8] = number8.getNumber();
        inputs[9] = number9.getNumber();
        inputs[10] = number10.getNumber();
        inputs[11] = number11.getNumber();
        inputs[12] = number13.getNumber();
        neuron.train(inputs, objectic, 0.001);

        Number number12 = new Number();
        number12.setNumber(0, 0);
        number12.setNumber(0, 1);
        number12.setNumber(0, 2);
        number12.setNumber(0, 3);
        number12.setNumber(1,0);
        number12.setNumber(1,4);
        number12.setNumber(2, 1);
        number12.setNumber(2, 2);
        number12.setNumber(2, 3);
        number12.setNumber(2, 4);
        number12.setNumber(3, 0);
        number12.setNumber(3, 4);
        number12.setNumber(4, 1);
        number12.setNumber(4, 2);
        number12.setNumber(4, 3);
        number12.setNumber(4, 4);


        System.out.println("Number Example:");
        number12.printNumber();
        double result = neuron.predict(number12.getNumber());
        if ( result<= 0) {
            System.out.println("0");
        }else if (result > 0 && result <= 1){
            System.out.println("1");
        } else if (result > 1 && result <= 2) {
            System.out.println("2");
        } else if (result > 2) {
            System.out.println("3");
        } else {
            System.out.println(result);
        }
    }
}

