package co.edu.unbosque.Model.Numeros;

public class Number {
    private final double[][] number;

    public Number() {
        number = new double[5][5];
    }

    public void setNumber(int i, int j) {

        number[i][j] = 1;
    }

    public double[] getNumber() {
        double[] result = new double[25];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                result[i * 5 + j] = number[i][j];
            }
        }
        return result;
    }

  
}
