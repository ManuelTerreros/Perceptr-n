package co.edu.unbosque.Model.Animales;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


public class Controller {
    private final ImageUtils imageUtils;
    private double[][] entradas;

    
    public Controller() {
        imageUtils = new ImageUtils();
        double[] etiquetas = new double[]{0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1,
                1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0};
        addModel();
        SimpleNeuron sn = new SimpleNeuron(16385);
        sn.train(entradas, etiquetas, 0.0001); // Menor valor de learning rate mejora la predicción, mayor valor de learning rate mejora la velocidad
        File folder = new File("Examples");
        File[] files = folder.listFiles();
        for (File file : files) {
            if (sn.predict(singleImage(imageUtils.whiteBlackSingle(file.getPath()))) <= 0) {
                System.out.println(file.getName() + ": No es un animal");
            } else {
                System.out.println(file.getName() + ": Es un animal");
            }
            System.out.println(sn.predict(singleImage(imageUtils.whiteBlackSingle(file.getPath()))));
        }
    }

    /**
     * Generates an array of normalized pixel values from a given image.
     *
     * @param image the BufferedImage object representing the image
     * @return an array of normalized pixel values
     */
    public double[] singleImage(BufferedImage image) {
        // image = imageUtils.applySobel(image);
        double[] pixels = image.getRaster().getPixels(0, 0, image.getWidth(), image.getHeight(), (double[]) null);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = pixels[i] / 255;
        }
        return pixels;
    }

    /**
     * Adds a model by creating it using the provided image directory.
     */
    public void addModel() {
        createModel(convert("src/main/resources/static"));
    }

    /**
     * Converts the image at the specified path to a black and white image.
     *
     * @param path the path of the image file to be converted
     * @return an ArrayList of BufferedImage objects representing the converted image
     */
    public ArrayList<BufferedImage> convert(String path) {
        return imageUtils.whiteBlack(path);
    }

    /**
     * Creates a model based on the given list of images.
     *
     * @param images the list of images to create the model from
     */
    public void createModel(ArrayList<BufferedImage> images) {
        entradas = new double[imageUtils.getNumber()][16384];
        int count = 0;
        for (BufferedImage image : images) {
            int width = image.getWidth();
            int height = image.getHeight();
            double[] pixels = image.getRaster().getPixels(0, 0, width, height, (double[]) null);
            for (int i = 0; i < pixels.length; i++) {
                entradas[count][i] = pixels[i] / 255;
            }
            count++;
        }

    }


}
