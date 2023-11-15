package co.edu.unbosque.Model.Colores;

public class Color {
    private double red;
    private double green;
    private double blue;

    public Color(double red, double green, double blue) {
        this.red = red / 255.0;
        this.green = green / 255.0;
        this.blue = blue / 255.0;
    }

    public double getRed() {
        return this.red;
    }

    public double getGreen() {
        return this.green;
    }

    public double getBlue() {
        return this.blue;
    }

}