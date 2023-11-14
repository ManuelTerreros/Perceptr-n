package co.edu.unbosque.Model.Animales;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageUtils {

    private final ColorConvertOp convertOp;
    private final int[][] SOBELY = {
            {-1, -2, -1},
            {0, 0, 0},
            {1, 2, 1}
    };
    private int number;

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
     * Downsamples an image by averaging the pixel values of each 2x2 block.
     *
     * @param image the BufferedImage to be downsampled
     * @return the downsampled BufferedImage
     */
    public BufferedImage downAverage(BufferedImage image) {
        BufferedImage resultImage = new BufferedImage(image.getWidth() / 2, image.getHeight() / 2, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < image.getWidth(); i += 2) {
            for (int j = 0; j < image.getHeight(); j += 2) {
                int pixel = (image.getRGB(i, j) + image.getRGB(i + 1, j) + image.getRGB(i, j + 1) + image.getRGB(i + 1, j + 1)) / 4;
                resultImage.setRGB(i / 2, j / 2, (pixel << 16) | (pixel << 8) | pixel);
            }
        }
        return resultImage;
    }

    /**
     * Apply a filter to a specific pixel in an image.
     *
     * @param image  the image to apply the filter to
     * @param x      the x-coordinate of the pixel
     * @param y      the y-coordinate of the pixel
     * @param filter the filter to apply
     * @return the filtered pixel value
     */
    public int applyFilter(BufferedImage image, int x, int y, int[][] filter) {
        int result = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int pixelValue = image.getRGB(x + i, y + j) & 0xFF;
                result += pixelValue * filter[i + 1][j + 1];
            }
        }
        return Math.min(255, Math.max(0, result));
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
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                Image resizedImage = image.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
                image = toBufferedImage(resizedImage);
                images.add(convertOp.filter(image, null));
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
}
