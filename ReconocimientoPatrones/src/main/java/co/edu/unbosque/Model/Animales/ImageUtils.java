package co.edu.unbosque.Model.Animales;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageUtils {

    private final ColorConvertOp convertOp;
    private int number;
    private ArrayList<Double> labels;

    public ImageUtils() {
        convertOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
    }

    /**
     * Converts an Image object to a BufferedImage object.
     *
     * @param image the Image object to be converted
     * @return the converted BufferedImage object
     */
    public BufferedImage toBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }


    /**
     * Reads an image from a file, resizes it to 128x128 pixels using a smooth scaling algorithm,
     * and applies a filter to convert the image to black and white.
     *
     * @param path the path to the image file
     * @return the resulting black and white image
     */
    public BufferedImage whiteBlackSingle(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            Image resizedImage = image.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
            image = toBufferedImage(resizedImage);
            return convertOp.filter(image, null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BufferedImage whiteBlackBuffer(BufferedImage image) {
        Image resizedImage = image.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
        image = toBufferedImage(resizedImage);
        return convertOp.filter(image, null);
    }

    /**
     * Generates an ArrayList of black and white images from the images located in the given path.
     *
     * @param path the path to the directory containing the images
     * @return an ArrayList of BufferedImage objects representing the black and white images
     */
    public ArrayList<BufferedImage> whiteBlack(String path) {
        number = 0;
        ArrayList<BufferedImage> images = new ArrayList<>();
        try {
            File folder = new File(path);
            File[] files = folder.listFiles();
            ArrayList<File> paths = new ArrayList<>();
            labels = new ArrayList<>();
            assert files != null;
            for (File file : files) {
                paths.addAll(List.of((file.listFiles())));
            }
            Collections.shuffle(paths);
            for (File file : paths) {
                BufferedImage image = ImageIO.read(file);
                Image resizedImage = image.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
                image = toBufferedImage(resizedImage);
                images.add(convertOp.filter(image, null));
                labels.add(Double.parseDouble(file.getName().split("\\.")[0].split("_")[1]));
                number++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Double> getLabels() {
        return labels;
    }
}
