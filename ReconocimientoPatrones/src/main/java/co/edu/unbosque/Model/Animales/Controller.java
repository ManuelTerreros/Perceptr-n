package co.edu.unbosque.Model.Animales;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class Controller {
    private final ImageUtils imageUtils;
    private double[] etiquetas;
    private double[][] entradas;

    public Controller() {
        imageUtils = new ImageUtils();
        addModel2("AnimalesObjetos", 0.0001);

    }

    @PostMapping(value = "/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestPart("image") MultipartFile image, @RequestParam("folder") String folder, @RequestParam("label") int label) {
        String newFileName = folder + "/" + UUID.randomUUID() + "_" + label;
        Path filePath = Paths.get(newFileName);
        try {
            Files.write(filePath, image.getBytes());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(newFileName);
    }

    public double[] singleImage(BufferedImage image) {
        double[] pixels = image.getRaster().getPixels(0, 0, image.getWidth(), image.getHeight(), (double[]) null);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = pixels[i] / 255;
        }
        return pixels;
    }


    @PostMapping("/addModel")
    public void addModel(@RequestParam("folder") String folder, @RequestParam("learningRate") double learningRate) {
        createModel(convert("images/" + folder));
        etiquetas = imageUtils.getLabels().stream().mapToDouble(Double::doubleValue).toArray();
        SimpleNeuronImg sn = new SimpleNeuronImg(16385);
        sn.train(entradas, etiquetas, learningRate); // Menor valor de learning rate mejora la predicción, mayor valor de learning rate mejora la velocidad
    }

    public void addModel2(String folder, double learningRate) {
        createModel(convert("images/" + folder));
        etiquetas = imageUtils.getLabels().stream().mapToDouble(Double::doubleValue).toArray();
        SimpleNeuronImg sn = new SimpleNeuronImg(16385);
        sn.train(entradas, etiquetas, learningRate); // Menor valor de learning rate mejora la predicción, mayor valor de learning rate mejora la velocidad
        File ot = new File("images/Examples");
        File[] files = ot.listFiles();
        assert files != null;
        for (File file : files) {
            if (sn.predictImg(singleImage(imageUtils.whiteBlackSingle(file.getPath()))) <= 0) {
                System.out.println(file.getName() + ": No es un animal");
            } else {
                System.out.println(file.getName() + ": Es un animal");
            }
            System.out.println(sn.predictImg(singleImage(imageUtils.whiteBlackSingle(file.getPath()))));
        }
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
