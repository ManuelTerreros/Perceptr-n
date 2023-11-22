package co.edu.unbosque.Model.Animales;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Component("animalsController")
@CrossOrigin(origins = "*")
@RequestMapping("/api/images")
public class Controller {
    private final ImageUtils imageUtils;
    private double[] etiquetas;
    private double[][] entradas;
    private final SimpleNeuronImg sn;

    public Controller() {
        imageUtils = new ImageUtils();
        sn = new SimpleNeuronImg(16385);
    }


    @PostMapping(value = "/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestPart("image") List<MultipartFile> images, @RequestPart("folder") String folder, @RequestPart("name") String name, @RequestPart("label") int label) {
        File folderFile = new File("images/" + folder);
        if (!folderFile.exists()) {
            folderFile.mkdirs();
        }
        File nameFolder = new File("images/" + folder + "/" + name);
        if (!nameFolder.exists()) {
            nameFolder.mkdirs();
        }
        String newFileName = folder + "/" + UUID.randomUUID() + "_" + label;
        Path filePath = Paths.get(newFileName);
        for (MultipartFile image : images) {
            try {
                Files.write(filePath, image.getBytes());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen");
            }
        }
        return ResponseEntity.ok(newFileName);
    }


    @PostMapping("/addModel")
    public ResponseEntity<String> addModel(@RequestParam("folder") String folder, @RequestParam("learningRate") double learningRate) {
        try {
            sn.resetWeights();
            createModel(convert("images/" + folder));
            etiquetas = imageUtils.getLabels().stream().mapToDouble(Double::doubleValue).toArray();
            int it = sn.train(entradas, etiquetas, learningRate); // Menor valor de learning rate mejora la predicción, mayor valor de learning rate mejora la velocidad
            saveWeights(folder);
            return ResponseEntity.ok("Modelo creado exitosamente iteraciones: " + it);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el modelo");
        }
    }

    @GetMapping("/getWeights")
    public ResponseEntity<String> getWeights(@RequestParam("name") String name) {
        File file = new File("models/" + name + ".txt");
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo no encontrado");
        }
        return ResponseEntity.ok().body(sb.toString());
    }

    @PostMapping("/showResult")
    public ResponseEntity<String> showResult(@RequestPart MultipartFile image, @RequestPart("name") String name) {
        try {
            List<String> lines = Files.readAllLines(Path.of("models/" + name + ".txt"), StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(line);
            }
            String[] lines2 = sb.toString().split("#");
            double[] weights = new double[lines2.length];
            for (int i = 0; i < lines2.length; i++) {
                weights[i] = Double.parseDouble(lines2[i]);
            }
            sn.setWeights(weights);
            byte[] imageBytes = image.getBytes();
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage endImage = ImageIO.read(bis);
            double prediction = sn.predictImg(singleImage(imageUtils.whiteBlackBuffer(endImage)));
            if (prediction <= 0) {
                return ResponseEntity.ok().body("No es un animal");
            } else {
                return ResponseEntity.ok().body("Es un animal");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Error al mostrar el resultado ");
        }
    }


    public ArrayList<BufferedImage> convert(String path) {
        return imageUtils.whiteBlack(path);
    }


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


    public double[] singleImage(BufferedImage image) {
        double[] pixels = image.getRaster().getPixels(0, 0, image.getWidth(), image.getHeight(), (double[]) null);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = pixels[i] / 255;
        }
        return pixels;
    }


    public void saveWeights(String name) {
        File save = new File("models/" + name + ".txt");
        try {
            save.createNewFile();
            double[] weights = sn.getWeights();
            FileWriter fw = new FileWriter(save);
            for (double weight : weights) {
                fw.write(weight + "#");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
