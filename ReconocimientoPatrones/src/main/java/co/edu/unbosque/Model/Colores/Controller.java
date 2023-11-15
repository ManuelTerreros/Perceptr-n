package co.edu.unbosque.Model.Colores;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component("coloresController")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/colors")
public class Controller {
    private final SimpleNeuronColor neuron;
    private List<double[]> colorList;
    private List<Double> targetList;

    public Controller() {
        neuron = new SimpleNeuronColor(4);
        initialize();
    }

    public void initialize() {
        double[] targets = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0};
        targetList = new ArrayList<>();
        for (double target : targets) {
            targetList.add(target);
        }
        Color white = new Color(255.0, 255.0, 255.0);
        Color black = new Color(0.0, 0.0, 0.0);
        Color lightBlue = new Color(0.0, 255.0, 255.0);
        Color darkBlue = new Color(0.0, 0.0, 255.0);
        Color red = new Color(255.0, 0.0, 0.0);
        Color green = new Color(0.0, 255.0, 0.0);
        Color lime = new Color(0.0, 255.0, 100.0);
        Color yellow = new Color(255.0, 255.0, 102.0);
        Color purple = new Color(102.0, 0.0, 255.0);
        Color orange = new Color(255.0, 165.0, 0.0);
        Color pink = new Color(255.0, 192.0, 203.0);
        Color lavender = new Color(230.0, 230.0, 250.0);
        Color maroon = new Color(128.0, 0.0, 0.0);
        Color olive = new Color(128.0, 128.0, 0.0);
        Color teal = new Color(0.0, 128.0, 128.0);
        Color cyan = new Color(0.0, 255.0, 255.0);
        Color magenta = new Color(255.0, 0.0, 255.0);
        Color brown = new Color(139.0, 69.0, 19.0);
        Color gray = new Color(128.0, 128.0, 128.0);
        Color lightGray = new Color(211.0, 211.0, 211.0);
        Color darkGray = new Color(169.0, 169.0, 169.0);
        Color navy = new Color(0.0, 0.0, 128.0);
        Color indigo = new Color(75.0, 0.0, 130.0);
        Color gold = new Color(255.0, 215.0, 0.0);
        Color sienna = new Color(160.0, 82.0, 45.0);
        Color tan = new Color(210.0, 180.0, 140.0);
        Color peach = new Color(255.0, 218.0, 185.0);
        Color plum = new Color(221.0, 160.0, 221.0);
        Color slateBlue = new Color(106.0, 90.0, 205.0);
        colorList = new ArrayList<>();
        colorList.add(addColor(white));
        colorList.add(addColor(black));
        colorList.add(addColor(lightBlue));
        colorList.add(addColor(darkBlue));
        colorList.add(addColor(red));
        colorList.add(addColor(green));
        colorList.add(addColor(lime));
        colorList.add(addColor(yellow));
        colorList.add(addColor(purple));
        colorList.add(addColor(orange));
        colorList.add(addColor(pink));
        colorList.add(addColor(lavender));
        colorList.add(addColor(maroon));
        colorList.add(addColor(olive));
        colorList.add(addColor(teal));
        colorList.add(addColor(brown));
        colorList.add(addColor(gray));
        colorList.add(addColor(cyan));
        colorList.add(addColor(peach));
        colorList.add(addColor(magenta));
        colorList.add(addColor(lightGray));
        colorList.add(addColor(darkGray));
        colorList.add(addColor(navy));
        colorList.add(addColor(indigo));
        colorList.add(addColor(gold));
        colorList.add(addColor(sienna));
        colorList.add(addColor(tan));
        colorList.add(addColor(plum));
        colorList.add(addColor(slateBlue));
    }

    public void train(double[][] inputs, double[] targets, double learningRate) {
        neuron.resetWeights();
        neuron.train(inputs, targets, learningRate);
        saveWeights("colores");
    }

    @PostMapping("/trainColor")
    public ResponseEntity<String> trainColor(@RequestParam("red") int red, @RequestParam("green") int green, @RequestParam("blue") int blue, @RequestParam("learningRate") double learningRate, @RequestParam("target") double target) {
        colorList.add(addColor(new Color(red, green, blue)));
        targetList.add(target);
        double[][] inputs = new double[colorList.size()][3];
        for (int i = 0; i < colorList.size(); i++) {
            inputs[i] = colorList.get(i);
        }
        double[] targets = new double[targetList.size()];
        for (int i = 0; i < targetList.size(); i++) {
            targets[i] = targetList.get(i);
        }
        train(inputs, targets, learningRate);
        return ResponseEntity.ok().body("Entrenamiento completado");

    }

    @PostMapping("/predictColor")
    public ResponseEntity<Integer> predecirColor(@RequestParam("red") int red, @RequestParam("green") int green, @RequestParam("blue") int blue) {
        File file = new File("models/colores.txt");
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1);
        }
        String[] weights = sb.toString().split("#");
        double tmp[] = new double[weights.length];
        for (int i = 0; i < weights.length - 1; i++) {
            tmp[i] = Double.parseDouble(weights[i]);
        }
        neuron.setWeights(tmp);
        double resultado = neuron.predictColor(new double[]{red, green, blue});
        try {
            if (resultado <= 0) {
                return ResponseEntity.ok().body(0);
            } else {
                return ResponseEntity.ok().body(1);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1);
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

    public double[] addColor(Color color) {
        return new double[]{color.getRed(), color.getGreen(), color.getBlue()};
    }


    public void saveWeights(String name) {
        File save = new File("models/" + name + ".txt");
        try {
            save.createNewFile();
            double[] weights = neuron.getWeights();
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

