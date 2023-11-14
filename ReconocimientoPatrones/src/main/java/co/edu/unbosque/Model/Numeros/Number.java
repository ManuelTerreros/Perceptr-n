package co.edu.unbosque.Model.Numeros;

public class Number {
    private final double[][] number;
    public Number(){
        number = new double[5][5];
    }
    /**
     * Sets the value of the number at the specified indices to 1.
     *
     * @param  i  the row index of the number
     * @param  j  the column index of the number
     */
    public void setNumber(int i, int j){

        number[i][j] = 1;
    }
    /**
     * Retrieves an array of numbers.
     *
     * @return  an array of doubles representing the numbers
     */
    public double[] getNumber(){
        double[] result = new double[25];
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                result[i*5+j] = number[i][j];
            }
        }
        return result;
    }
    public void printNumber(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                System.out.print(number[i][j] + " ");
            }
            System.out.println();
        }
    }
}
