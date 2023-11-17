package co.edu.unbosque.Model.Numeros;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Component("numbersController")
@CrossOrigin(origins = "*")
@RequestMapping("/api/numbers")
public class Controller {
    private SimpleNeuronNumber firstNeuron;
    private SimpleNumberNeuronV2 secondNeuron;
    private NumeroV1 firstNumber;
    private NumeroV2 secondNumber;


    public Controller() {
        firstNeuron = new SimpleNeuronNumber(26);
        secondNeuron = new SimpleNumberNeuronV2(26);

    }

    public void test() {
        initializeV1();
        initializeV2();
    }

    public void initializeV1() {
        firstNumber = new NumeroV1();
        firstNeuron.setWeights(firstNumber.getWeidt());
        saveWeightsV1("numerosV1");
    }

    public void initializeV2() {
        secondNumber = new NumeroV2();
        secondNeuron.setWeights(secondNumber.getWeidt());
        saveWeightsV2("numerosV2");
    }

    @GetMapping("/getWeightsV1")
    public ResponseEntity<String> getWeightsV1(@RequestParam("name") String name) {
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

    @GetMapping("/getWeightsV2")
    public ResponseEntity<String> getWeightsV2(@RequestParam("name") String name) {
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

    public void saveWeightsV1(String name) {
        File save = new File("models/" + name + ".txt");
        try {
            save.createNewFile();
            double[] weights = firstNeuron.getWeights();
            FileWriter fw = new FileWriter(save);
            for (double weight : weights) {
                fw.write(weight + "#");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWeightsV2(String name) {
        File save = new File("models/" + name + ".txt");
        try {
            save.createNewFile();
            double[] weights = secondNeuron.getWeights();
            FileWriter fw = new FileWriter(save);
            for (double weight : weights) {
                fw.write(weight + "#");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/trainNumber/V1")
    public ResponseEntity<String> trainNumberV1() {
        try {
            initializeV1();
            return ResponseEntity.ok().body("Entrenamiento completado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo no encontrado");
        }


    }

    @PostMapping("/trainNumber/V2")
    public ResponseEntity<String> trainNumberV2() {
        try {
            initializeV2();
            return ResponseEntity.ok().body("Entrenamiento completado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo no encontrado");
        }
    }

    @PostMapping("/result/V1")
    public ResponseEntity<String> resultV1(@RequestParam("matriz") String newMatriz) {
        try {
            File file = new File("models/numerosV1.txt");
            StringBuilder sb = new StringBuilder();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            fr.close();
            String[] weights = sb.toString().split("#");
            double tmp[] = new double[weights.length];
            for (int i = 0; i < weights.length; i++) {
                tmp[i] = Double.parseDouble(weights[i]);
            }
            firstNeuron.setWeights(tmp);
            String[] numebers = newMatriz.split(",");

            double[] result = new double[25];
            for (int i = 0; i < result.length; i++) {
                result[i] = Double.parseDouble(numebers[i]);
            }
            double prediction = firstNeuron.predictNumber(result);
            if (prediction <= 0) {
                prediction = 0;
            } else if (prediction > 0 && prediction <= ((double) 1 / 3)) {
                prediction = 1;
            } else if (prediction > ((double) 1 / 3) && prediction <= ((double) 2 / 3)) {
                prediction = 2;
            } else if (prediction > ((double) 2 / 3) && prediction <= 1) {
                prediction = 3;
            } else if (prediction > 1) {
                prediction = 4;
            }
            return ResponseEntity.ok().body("El el numero es " + prediction);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo no encontrado");
        }
    }

    @PostMapping("/result/V2")
    public ResponseEntity<String> resultV2(@RequestParam("matriz") String newMatriz) {
        try {
            System.out.println(newMatriz);
            File file = new File("models/numerosV2.txt");
            StringBuilder sb = new StringBuilder();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            fr.close();
            System.out.println(sb.toString());
            String[] weights = sb.toString().split("#");
            double tmp[] = new double[weights.length];
            for (int i = 0; i < weights.length; i++) {
                tmp[i] = Double.parseDouble(weights[i]);
            }
            secondNeuron.setWeights(tmp);
            String[] numebers = newMatriz.split(",");
            double[] result = new double[25];
            for (int i = 0; i < result.length; i++) {
                result[i] = Double.parseDouble(numebers[i]);

            }
            double prediction = secondNeuron.predict(result);

            if (prediction <= ((double) 5 / 7)) {
                prediction = 5;
            } else if (prediction > ((double) 5 / 7) && prediction <= ((double) 6 / 7)) {
                prediction = 6;
            } else if (prediction > ((double) 6 / 7) && prediction <= 1) {
                prediction = 7;
            } else if (prediction > 1) {
                prediction = 8;
            }
            return ResponseEntity.ok().body("El el numero es " + prediction);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo no encontrado");
        }
    }


}
