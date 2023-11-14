package co.edu.unbosque.Model.Colores;

public record Color(double red, double green, double blue) {
    public Color(double red, double green, double blue) {
        this.red = red / 255;
        this.green = green / 255;
        this.blue = blue / 255;
    }
}
